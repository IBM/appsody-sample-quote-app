const app = require('express')()

app.set('views',__dirname + '/views');
app.set('view engine', 'pug')

app.get('/', (req, res) => {
  // res.send("Hello from Appsody!");
  res.redirect('/quote');
});

var quote = require('./quote.js');
app.use('/quote', quote);

module.exports.app = app;
