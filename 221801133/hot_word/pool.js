var mysql = require('D:/node/hot/hot_word/node_modules/mysql');

/*
 * 创建连接池。
 */
var create = function(config) {
    var pool  = mysql.createPool({
        connectionLimit: 200,
        acquireTimeout: 30000,
        host: config.host,
        port: config.port,
        user: config.user,
        password: config.password,
        database: config.database,
    });
    return pool;
};

exports.create = create;
