for (var n = 0; n < data.length; n++) {
    //获取div
    var div = document.getElementById("searchResult");

    //换行
    var br = document.createElement("br");
    div.appendChild(br);

    //添加label ，存放指标名称
    var div2 = document.createElement("label");
    div2.innerText = data[n].QualitativeTargetName;
    div.appendChild(div2);

    //添加text ，存放指标权重
    var input = document.createElement("input");
    input.setAttribute('type', 'text');
    input.setAttribute('ReadOnly', 'True');
    input.value = data[n].DevelopmentAllWeight
    div.appendChild(input);

    //添加select 存放指标id
    var targetID = document.createElement("select");
    targetID.innerText = data[n].QualitativeTargetID;
    targetID.setAttribute('hidden', 'hidden');
    div.appendChild(targetID);
    //添加 %(单位百分比)

    //换行
    var br = document.createElement("br");
    div.appendChild(br);
}
