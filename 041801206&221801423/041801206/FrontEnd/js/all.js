
    var searchWord_2 = document.getElementById("searchWord_2");
    var searchbtn_2 = document.getElementById("searchbtn_2");
    var aaa= document.getElementById("aaa");
    var par=document.getElementById("searchResult");
    var lodpar=document.getElementById("mcol");
    var resNumDiv = document.getElementById("resNumDiv");
    var xmlhttp;
    var searchPaperByKeywordUrl = "http://47.119.130.124:90/searchPaper";

    // var searchPaperByKeywordUrl = "http://localhost:90/searchPaper";

    function getQueryVariable(variable)
    {
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i=0;i<vars.length;i++) {
            var pair = vars[i].split("=");
            if(pair[0] == variable){return pair[1];}
        }
        return(false);
    }



        function deleteItem(Obj) {
            Obj.parentNode.parentNode.removeChild(Obj.parentNode);
        }


    window.onload=function(){

        var sw=getQueryVariable("sw");
        if(sw == false) sw="learning (artificial intelligence)";
        sw = sw.replace("%20"," ");
        if(window.XMLHttpRequest){
            xmlhttp = new XMLHttpRequest();
        }else{
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP")

        }
        var lod = document.createElement("div");
        lod.setAttribute('class', 'spinner-grow');
        lod.setAttribute('style', 'width: 3rem; height: 3rem;');
        lod.setAttribute('id', 'lodTemp');
        var lods=document.createElement("span");
        lods.setAttribute('class', 'sr-only');
        lod.appendChild(lods);
        lodpar.appendChild(lod);

        xmlhttp.onreadystatechange = function() {

            if(xmlhttp.readyState == 4){

                if(xmlhttp.status == 200) {
                    var str = xmlhttp.responseText;
                    var tempRem=document.getElementById("lodTemp");
                    lodpar.removeChild(tempRem);
                    var arr = eval('('+str+')');
                    var resNumDiv2=document.createElement("div");
                    resNumDiv2.setAttribute("class","alert alert-primary");
                    resNumDiv2.setAttribute("role","alert");
                    resNumDiv2.innerText="搜索词：\""+sw+"\"    已为您搜索到"+arr.length+"个结果！";
                    resNumDiv.appendChild(resNumDiv2);
                    for(var i=0;i<arr.length;i++){
                        var cardHeaderDiv=document.createElement("div");
                        cardHeaderDiv.setAttribute("class","typeAndyear");
                        cardHeaderDiv.setAttribute("style","text-align: right;");
                        cardHeaderDiv.innerText=arr[i].typeandyear + " —— " + arr[i].releasetime;

                        var  cardBodyDiv=document.createElement("div");
                        cardBodyDiv.setAttribute("class","card-body");

                        var bodyH5=document.createElement("h5");
                        bodyH5.setAttribute("class","card-title");
                        bodyH5.innerText=arr[i].title;
                        cardBodyDiv.appendChild(bodyH5);

                        var bodyP = document.createElement("p");
                        bodyP.setAttribute("class","card-text");
                        bodyP.innerText=arr[i].abstractcontext;
                        cardBodyDiv.appendChild(bodyP);

                        var bodyPB = document.createElement("p");
                        bodyPB.setAttribute("class","card-text");
                        bodyPB.setAttribute("style","font-weight: bold;");
                        bodyPB.innerText="KeyWord: "+arr[i].keyword;
                        cardBodyDiv.appendChild(bodyPB);

                        var bodyA = document.createElement("a");
                        bodyA.setAttribute("href",arr[i].link);
                        bodyA.setAttribute("class","btn btn-primary");
                        bodyA.innerText="查阅原文";
                        cardBodyDiv.appendChild(bodyA);

                        var cardDiv = document.createElement("div");
                        cardDiv.setAttribute('class', 'card');
                        cardDiv.setAttribute('style', 'margin:15px;padding:10px;border-radius: 8px;');

                        var deleteButton = document.createElement("button");
                        deleteButton.setAttribute("type","button");
                        deleteButton.setAttribute("onclick","deleteItem(this)");
                        deleteButton.setAttribute("class","btn btn-light");
                        deleteButton.setAttribute("style","width: 40px;")
                        deleteButton.innerText = "X";
                        cardDiv.appendChild(deleteButton);

                        cardDiv.appendChild(cardHeaderDiv);
                        cardDiv.appendChild(cardBodyDiv);
                        par.appendChild(cardDiv);
                    }

                }
            }
        }
        xmlhttp.open("POST",searchPaperByKeywordUrl,true);
        xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");
        xmlhttp.send("{\"searchKeyword\":\""+sw+"\"}");
    }

    searchbtn_2.onclick= function(){

        sw = searchWord_2.value;
        if (sw.length == 0 ){
            alert("输入有误！");
        }
        else {

            try {
                var id3Div= resNumDiv.getElementsByTagName('div');
                resNumDiv.removeChild(id3Div[0]);

                var id2Div= par.getElementsByTagName('div');
                var length = id2Div.length;
                for (var i = 0; i <length; i++) {
                    par .removeChild(id2Div[0]);
                }
            }catch (e) {

            }

            if(window.XMLHttpRequest){
                xmlhttp = new XMLHttpRequest();
            }else{
                xmlhttp = new ActiveXObject("Microsoft.XMLHTTP")
            }
            var lod = document.createElement("div");
            lod.setAttribute('class', 'spinner-grow');
            lod.setAttribute('style', 'width: 3rem; height: 3rem;');
            lod.setAttribute('id', 'lodTemp');
            var lods=document.createElement("span");
            lods.setAttribute('class', 'sr-only');
            lod.appendChild(lods);
            lodpar.appendChild(lod);


            xmlhttp.onreadystatechange = function() {

                if(xmlhttp.readyState == 4){

                    if(xmlhttp.status == 200) {
                        var str = xmlhttp.responseText;
                        var tempRem=document.getElementById("lodTemp");
                        lodpar.removeChild(tempRem);
                        var arr = eval('('+str+')');
                        var resNumDiv2=document.createElement("div");
                        resNumDiv2.setAttribute("class","alert alert-primary");
                        resNumDiv2.setAttribute("role","alert");
                        resNumDiv2.innerText="搜索词：\""+sw+"\"    已为您搜索到"+arr.length+"个结果！";
                        resNumDiv.appendChild(resNumDiv2);
                        for(var i=0;i<arr.length;i++){
                            console.log(arr[i].link);



                            var cardHeaderDiv=document.createElement("div");
                            cardHeaderDiv.innerText=arr[i].typeandyear+" —— "+arr[i].releasetime;

                            var  cardBodyDiv=document.createElement("div");
                            cardBodyDiv.setAttribute("class","card-body");

                            var bodyH5=document.createElement("h5");
                            bodyH5.setAttribute("class","card-title");
                            bodyH5.innerText=arr[i].title;
                            cardBodyDiv.appendChild(bodyH5);

                            var bodyP = document.createElement("p");
                            bodyP.setAttribute("class","card-text");
                            bodyP.innerText=arr[i].abstractcontext;
                            cardBodyDiv.appendChild(bodyP);

                            var bodyPB = document.createElement("p");
                            bodyPB.setAttribute("class","card-text");
                            bodyPB.setAttribute("style","font-weight: bold;");
                            bodyPB.innerText="KeyWord: "+arr[i].keyword;
                            cardBodyDiv.appendChild(bodyPB);

                            var bodyA = document.createElement("a");
                            bodyA.setAttribute("href",arr[i].link);
                            bodyA.setAttribute("class","btn btn-primary");
                            bodyA.innerText="查阅原文";
                            cardBodyDiv.appendChild(bodyA);

                            var cardDiv = document.createElement("div");
                            cardDiv.setAttribute('class', 'card');
                            cardDiv.setAttribute('style', 'margin:15px;padding:10px;border-radius: 8px;');
                            cardDiv.appendChild(cardHeaderDiv);
                            cardDiv.appendChild(cardBodyDiv);
                            par.appendChild(cardDiv);
                        }

                    }
                }
            }
        }

        xmlhttp.open("POST",searchPaperByKeywordUrl,true);
        xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");
        xmlhttp.send("{\"searchKeyword\":\""+sw+"\"}");
    }



