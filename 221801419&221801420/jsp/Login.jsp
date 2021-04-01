<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>vcEssay:Login</title>
    <script src="https://cdn.staticfile.org/vue/2.4.2/vue.min.js"></script>
    <style>

        *{
            margin:0;
            padding:0;
        }

        a, a:visited {
            outline:none;
            color:#389dc1;
        }

        a:hover{
            text-decoration:none;
        }

        section, footer, header, aside, nav{
            display: block;
        }

        body{
            font:15px/1.3 'Open Sans', sans-serif;
            color: #5e5b64;
            text-align:center;
        }


        section, footer, header, aside, nav{
            display: block;
        }


        nav{
            width:800px;
            display:inline-block;
            background-color: #010505;
            border-radius:2px;
        }

        nav a{
            display:inline-block;
            padding: 18px 30px;
            color:#fff !important;
            font-weight:bold;
            font-size:16px;
            text-decoration:none !important;
            line-height:1;
            text-transform: uppercase;
            background-color:transparent;
        }
        nav img{
            margin-right: 10px;
        }

        nav a:first-child{
            border-radius:2px 0 0 2px;
        }

        nav a:last-child{
            border-radius:0 2px 2px 0;
        }

        nav.index .index,
        nav.list .list,
        nav.hotWord .hotWord,
        nav.login .login{
            background-color: #0bb876;
        }

        p{
            font-size:22px;
            font-weight:bold;
            color:#7d9098;
        }

        p b{
            color:#ffffff;
            display:inline-block;
            padding:5px 10px;
            background-color:#c4d7e0;
            border-radius:2px;
            text-transform:uppercase;
            font-size:18px;
        }

        .bar{
            background-color:#5c9bb7;

            background-image:-webkit-linear-gradient(top, #5c9bb7, #5392ad);
            background-image:-moz-linear-gradient(top, #5c9bb7, #5392ad);
            background-image:linear-gradient(top, #5c9bb7, #5392ad);

            border-radius: 2px;
            width: 400px;
            padding: 1px;
            margin: 45px auto 20px;
            position:relative;
        }

        .bar input{
            background: #fff url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyBpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMC1jMDYwIDYxLjEzNDc3NywgMjAxMC8wMi8xMi0xNzozMjowMCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNSBXaW5kb3dzIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOkU5NEY0RTlFMTA4NzExRTM5RTEzQkFBQzMyRjkyQzVBIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOkU5NEY0RTlGMTA4NzExRTM5RTEzQkFBQzMyRjkyQzVBIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6RTk0RjRFOUMxMDg3MTFFMzlFMTNCQUFDMzJGOTJDNUEiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6RTk0RjRFOUQxMDg3MTFFMzlFMTNCQUFDMzJGOTJDNUEiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz4DjA/RAAABK0lEQVR42pTSQUdEURjG8dOY0TqmPkGmRcqYD9CmzZAWJRHVRIa0iFYtM6uofYaiEW2SRJtEi9YxIklp07ZkWswu0v/wnByve7vm5ee8M+85zz1jbt9Os+WiGkYdYxjCOx5wgFeXUHmtBSzpcCGa+5BJTCjEP+0nKWAT8xqe4ArPGEEVC1hHEbs2oBwdXkM7mj/JLZrad437sCGHOfUtcziutuYu2v8XUFF/4f6vMK/YgAH1HxkBYV60AR31gxkBYd6xAeF3VzMCwvzOBpypX8V4yuFRzX2d2gD/l5yjH4fYQEnzkj4fae5rJulF2sMXVrAsaTWttRFu4Osb+1jEDT71/ZveyhouTch2fINQL9hKefKjuYFfuznXWzXMTabyrvfyIV3M4vhXgAEAUMs7K0J9UJAAAAAASUVORK5CYII=) no-repeat 13px 13px;

            border: none;
            width: 100%;
            line-height: 19px;
            padding: 11px 0;

            border-radius: 2px;

            text-align: left;
            font-size: 14px;
            font-family: inherit;
            color: #738289;
            font-weight: bold;
            outline: none;
            text-indent: 40px;
        }


    </style>

</head>
<body>

<form id="main">

    <!-- 激活的菜单样式为  active 类 -->
    <!-- 为了阻止链接在点击时跳转，我们使用了 "prevent" 修饰符 (preventDefault 的简称)。 -->

    <nav v-bind:class="active" v-on:click>

        <!-- 当菜单上的链接被点击时，我们调用了 makeActive 方法, 该方法在 Vue 实例中创建。 -->

        <a href="index.jsp" class="index" v-on:click="makeActive('index')">A</a>
        <a href="essayList.jsp" class="list" v-on:click="makeActive('list')">B</a>
        <a href="essayHotWord.jsp" class="hotWord" v-on:click="makeActive('hotWord')">C</a>
        <a href="#" class="login" v-on:click="makeActive('login')">D</a>

    </nav>


    <p><b>{{active}} </b></p>


</form>

<script>
    // 创建一个新的 Vue 实例
    var demo = new Vue({
        // DOM 元素，挂载视图模型
        el: '#main',

        // 定义属性，并设置初始值
        data: {
            active: 'login',
            searchString: "",

        },

        // 点击菜单使用的函数
        methods: {
            makeActive: function(item){
                // 模型改变，视图会自动更新
                this.active = item;
            }
        }
    });
</script>
</body>
</html>