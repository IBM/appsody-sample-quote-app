var expect = require('chai').expect;
var request = require('request');

// Start the server before testing
const server = require('../../server').server;
const PORT = require('../../server').PORT;
const url = "http://localhost:" + PORT;

describe('Node.js Express Simple template', function () {
    // Testing / endpoint, should return 200
    describe('/ endpoint', function () {
        it('status', function (done) {
            request(url + '/', function (error, response, body) {
                expect(response.statusCode).to.equal(200);
                done();
            });
        });
    });
});

describe('GET /quote', function () {
    // Testing /quote endpoint, should return 200
    describe('/quote endpoint', function () {
        it('status', function (done) {
            request(url + '/quote', function (error, response, body) {
                expect(response.statusCode).to.equal(200);
                done();
            });
        });
    });
});

describe('POST /quote', function () {
    // Testing /quote endpoint, should return 200
    describe('/quote endpoint', function () {
        it('status', function (done) {
            request.post(url + '/quote', {form:{age:'55',weight:'180',feet:'6',inches:'0',gender:'male',smoker:'never'}}, function (error, response, body) {
                expect(response.statusCode).to.equal(200);
                done();
            });
        });
    });
});

// Stop the server after testing
after(done => {
    server.close(done);
});