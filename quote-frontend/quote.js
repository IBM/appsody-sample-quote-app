const express = require('express');
const router = express.Router();
const bodyParser = require('body-parser');
const request = require("request");
const { check, validationResult } = require('express-validator');

process.env["NODE_CONFIG_DIR"] = __dirname + "/config/";
const config = require('config');
const backendUrl = config.get('backendUrl');

router.use(bodyParser.urlencoded({ extended: true }));

router.get('/', function(req, res){
  res.render('quote');
});

router.post('/',
  [ check('age').isNumeric().withMessage("age is required and must be a number"),
    check('weight').isNumeric().withMessage("weight is required and must be a number"),
    check('feet').isNumeric().withMessage("height in feet is required and must be a number"),
    check('inches').isNumeric().withMessage("height in inches is required and must be a number"),
    check('gender').not().isEmpty().withMessage("gender selection is required"),
    check('smoker').not().isEmpty().withMessage("smoker selection is required"),
  ],
  function(req, res){
    console.log("Received form", req.body);
    const errors = validationResult(req);
    if (!errors.isEmpty()) {
      var result = req.body;
      result.errors = errors.array();
      return res.render('quote', result);
    }
    // Set up backend request with appropriate type and unit conversions
    var backendRequest = {};
    backendRequest.age = Number(req.body.age);
    backendRequest.weight = Number(req.body.weight) * 0.453592; // pounds to kg
    backendRequest.height = Number(req.body.feet) * 30.48 + Number(req.body.inches) * 2.54;
    backendRequest.smoker = req.body.smoker;
    backendRequest.gender = req.body.gender;
    backendRequest.cancer = Boolean(req.body.cancer === 'true');
    backendRequest.cvd = Boolean(req.body.cvd === 'true');
    // Call backend API
    console.log("Calling backend API at " + backendUrl);
    var theQuote = -1;
    var theBasis = "";
    request.post({
      "url": backendUrl,
      "json": true,
      "body": backendRequest
    }, (error, response, body) => {
      if(error) {
        console.log(error);
      } else if(response.statusCode != 200) {
        console.log("Status code ", response.statusCode);
      } else {
        console.log("Success", body);
        theQuote = body.quotedAmount;
        theBasis = body.basis;
      }
      // Redisplay form with form inputs and quoted value.
      var result = req.body;
      result.quotedAmount = theQuote;
      result.basis = theBasis;
      res.render('quote', result);
    });
});

// Scaffold backend api for development testing
router.post('/test', function(req,res) {
  res.json({ "quotedAmount": 123, "basis": "mocked frontend computation" });
});

//export this router to use in app.js
module.exports = router;