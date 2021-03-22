$(document).ready(function(){
    bgImageTotal=20;
    randomNumber = Math.round(Math.random()*(bgImageTotal-1))+1;
    imgPath=('/img/bg/'+randomNumber+'.jpg');
    $('h1').css('background-image', ('url("'+imgPath+'")'));
});