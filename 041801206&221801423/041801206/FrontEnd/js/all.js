
    var searchbtn_1 = document.getElementById("searchbtn_1");
    var searchbtn_2 = document.getElementById("searchbtn_2");
    var aaa= document.getElementById("aaa");
    var par=document.getElementById("searchResult");
    var lodpar=document.getElementById("mcol");
    var searchword ;
    var xmlhttp;
    var searchPaperByKeywordUrl = "http://47.119.130.124:90/searchPaperByKeyword";



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

    window.onload=function(){
        var sw=getQueryVariable("sw");alert(sw);
        if(window.XMLHttpRequest){
            xmlhttp = new XMLHttpRequest();
        }else{
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP")

        }
        var lod = document.createElement("div");
        lod.setAttribute('class', 'spinner-border');
        lod.setAttribute('id', 'lodTemp');
        var lods=document.createElement("span");
        lods.setAttribute('class', 'sr-only');
        lod.appendChild(lods);
        lodpar.appendChild(lod);


        xmlhttp.onreadystatechange = function() {

            if(xmlhttp.readyState == 4){

                if(xmlhttp.status == 200) {
                    var str = xmlhttp.responseText;alert(str);
                    var tempRem=document.getElementById("lodTemp");
                    lodpar.removeChild(tempRem);
                    var arr = eval('('+str+')');
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
        xmlhttp.open("POST",searchPaperByKeywordUrl,true);
        xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");
        xmlhttp.send("{\"searchKeyword\":\""+sw+"\"}");
    }


    searchbtn_2.onclick= function(){
        var sw=getQueryVariable("sw");alert(sw);
        if(window.XMLHttpRequest){
            xmlhttp = new XMLHttpRequest();
        }else{
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP")

        }
        var lod = document.createElement("div");
        lod.setAttribute('class', 'spinner-border');
        lod.setAttribute('id', 'lodTemp');
        var lods=document.createElement("span");
        lods.setAttribute('class', 'sr-only');
        lod.appendChild(lods);
        lodpar.appendChild(lod);

        // <div class="card">
        //         <div class="card-header">
        //         Featured
        //         </div>
        //         <div class="card-body">
        //         <h5 class="card-title">Special title treatment</h5>
        //     <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
        //     <a href="#" class="btn btn-primary">Go somewhere</a>
        //     </div>
        //     </div>

        xmlhttp.onreadystatechange = function() {

            if(xmlhttp.readyState == 4){

                if(xmlhttp.status == 200) {
                    var str = xmlhttp.responseText;alert(str);
                    var tempRem=document.getElementById("lodTemp");
                    lodpar.removeChild(tempRem);
                    var arr = eval('('+str+')');
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
        xmlhttp.open("POST",searchPaperByKeywordUrl,true);
        xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");
        xmlhttp.send("{\"searchKeyword\":\""+sw+"\"}");
    }



