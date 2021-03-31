function stringHan(str)//处理字符串
{
    let reg = new RegExp("\"","g");
    let c = str.replace(reg,"");
    let newStr = c.replace(/\[|]/g,'');    
    return newStr;
}
function stringHandleYear(str)//处理字符串
{
    let newStr = stringHandle(str);   
    let d = new Array();
    d = newStr.split(" ");         
    return d;
}
function stringSplit(str){//处理原表中的year列
    let words = new Array();
    words = str.split(" ");
    return words;
}
function stringSplitByDot(str){//处理原表中的year列
    let words = new Array();
    words = str.split(",");
    return words;
}
module.exports = {
    stringHan,
    stringHandleYear,
    stringSplit,
    stringSplitByDot
}
