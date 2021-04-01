var pool = require('./pool.js')
var db_config = {
    host: "localhost",
    user: "root",
    password: "",
    port: "3306",
    database: "java2e"
}
var poolen = pool.create(db_config);
var db = {};
db.query = function () {
    var sql, para, callback;
    if (arguments.length == 2) {
        sql = arguments[0];
        callback = arguments[1];
    } else {
        sql = arguments[0];
        para = arguments[1];
        callback = arguments[2];
    }
    if (!sql) {
        callback();
        return;
    }
    if (!para) {
        para = [];
    }
    poolen.query(sql, para, function(err, rows, fields) {  
        if (err) {  
          console.log(err);  
          callback(err, null);  
          return;  
        };
        callback(null, rows, fields);  
      });
}
module.exports = db;