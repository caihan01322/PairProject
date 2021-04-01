let fs  = require("fs");
var mapList = new Map();
var words = new Array();
let mysql = require('./connectMysql.js');
const schedule = require('node-schedule');


mysql.connectSql();
mysql.selYear();

setTimeout(() => {
    mysql.closeMysql();
}, 5000);


// fs.readFile('test.txt',function(err,data){
//     if (err) {
//         return console.error(err);
//     }
//     let wordList = data.toString();
//     mapList = wordAnal(wordList);
//     let b = mapList.get("Image Center");
//     console.log(mapList);
//    let d = 0;
//    mapList.forEach(function(value,key) {
//     let c = mysql.insertKeywords(d++,key,value);
//     //console.log(words[1]);
//     })
// })

// function wordAnal(wordList){
//     words = wordList.split(", ");
//    // console.log(words);
//     var myMap = new Map();
//     for(let i = 0; i < words.length;i++){
//         if(!myMap.has(words[i])){
//             myMap.set(words[i],1);
//         }else{
//             let a = myMap.get(words[i]);
//             a++;
//             myMap.set(words[i],a);
//         }
//     }
//     return myMap;
// }
