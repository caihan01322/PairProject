const express  = require("D:/node/hot/hot_word/node_modules/express");
const app = express();

app.use('/page',require('./pagePost.js'));
app.listen(3000,()=>{
    console.log('running');
})