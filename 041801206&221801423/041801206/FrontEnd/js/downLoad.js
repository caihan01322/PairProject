

var par=document.getElementById("result");
var lodpar=document.getElementById("mcol");
var resNumDiv = document.getElementById("resNumDiv");
var xmlhttp;
var getDownLoadPaperUrl = "http://47.119.130.124:90/getDownLoadPaper";
var downLoadPaperUrl = "http://47.119.130.124:90/downloadFile";


window.onload=function(){

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
                resNumDiv2.innerText="本库存有"+arr.length+"件论文，可供下载！";
                resNumDiv.appendChild(resNumDiv2);

// <div class="card">
//     <div class="card-body">
//     <h5 class="card-title">Special title treatment</h5>
// <p class="card-text">With supporting text below as a natural lead-in to additional content.</p>
// <a href="#" class="btn btn-primary">Go somewhere</a>
// </div>
// </div>

                for(var i=0;i<arr.length;i++){
                    console.log(arr[i].size);
                    var  cardBodyDiv=document.createElement("div");
                    cardBodyDiv.setAttribute("class","card-body");

                    var bodyH5=document.createElement("h5");
                    bodyH5.setAttribute("class","card-title");
                    bodyH5.innerText=arr[i].fileName;
                    cardBodyDiv.appendChild(bodyH5);

                    var bodyP = document.createElement("p");
                    bodyP.setAttribute("class","card-text");
                    bodyP.innerText="大小："+arr[i].size;
                    cardBodyDiv.appendChild(bodyP);

                    var dlbtn = document.createElement("button");
                    dlbtn.setAttribute("onclick","downloadThis(this)");
                    dlbtn.setAttribute("class","btn btn-primary");
                    dlbtn.innerText="下载原文";
                    cardBodyDiv.appendChild(dlbtn);

                    var cardDiv = document.createElement("div");
                    cardDiv.setAttribute('class', 'card shadow p-3 mb-5 bg-white rounded');
                    cardDiv.setAttribute('style', 'margin:10px;border-radius: 8px;');
                    cardDiv.setAttribute('id', arr[i].fileName);

                    var deleteButton = document.createElement("button");
                    deleteButton.setAttribute("type","button");
                    deleteButton.setAttribute("onclick","deleteItem(this)");
                    deleteButton.setAttribute("class","btn btn-light");
                    deleteButton.setAttribute("style","width: 40px;")
                    deleteButton.innerText = "X";


                    cardDiv.appendChild(deleteButton);
                    cardDiv.appendChild(cardBodyDiv);
                    par.appendChild(cardDiv);
                }

            }
        }
    }
    xmlhttp.open("POST",getDownLoadPaperUrl,true);
    xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");
    xmlhttp.send("{\"searchKeyword\":\"123\"}");
}

function deleteItem(Obj) {
    Obj.parentNode.parentNode.removeChild(Obj.parentNode);
}

function downloadThis(Obj) {
    var pobj = Obj.parentNode.parentNode;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP")
    }
    xmlhttp.open("POST", downLoadPaperUrl, true);
    xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");
    xmlhttp.send("{\"dlFileName\":\"" + pobj.getAttribute("id") + "\"}");

    xmlhttp.onreadystatechange = function () {

        if (xmlhttp.readyState == 4) {

            if (xmlhttp.status == 200) {
                var str = xmlhttp.responseText;
                var elink = document.createElement('a');
                elink.download = "【geiyepa】"+pobj.getAttribute("id");
                elink.style.display = 'none';
                var blob = new Blob([str]);
                elink.href = URL.createObjectURL(blob);
                document.body.appendChild(elink);
                elink.click();
                document.body.removeChild(elink);
            }
        }
    }
}

