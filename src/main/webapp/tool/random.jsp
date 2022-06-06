<%@page contentType="text/html; charset=UTF-8" %>

<html>
    <head>
        <titie></titie>
        <script>
            function random1() {
                window.location.href = "/tool/random1";
            }

            function random2() {
                var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function () {
                    if (xhr.readyState == 4 && xhr.status == 200) {
                        var data = xhr.responseText;
                        var showName = document.getElementById("showName");
                        showName.innerText = data;
                    }
                };
                xhr.open("get", "/tool/random2", true);
                xhr.send();
            }

            function random3() {
                var names = ["樊书琪", "杨治宇", "毛晓睿", "许文韬", "杨争鸣", "高超"];
                var random = Math.random();
                var floor = Math.floor(random * 6);
                document.getElementById("show_name").innerText = names[floor];
            }

            function startRoleCall() {
                var random = Math.random();
                var floor = Math.floor(random * 6);

                // var ths = document.getElementsByTagName("th");
                var ths = document.getElementsByClassName("c1");
                for (var i = 0; i < ths.length; i++) {
                    ths[i].style.backgroundColor = "white";
                }
                document.getElementById("s" + floor).style.backgroundColor = "green";
            }

            function click() {
                var random = Math.random();
                var floor = Math.floor(random * 6);

                var ths = document.getElementsByClassName("c2");
                for (var i = 0; i < ths.length; i++) {
                    ths[i].style.backgroundColor = "white";
                }
                document.getElementById("t" + floor).style.backgroundColor = "green";

            }

            function clickFlower() {
                var ss = setInterval(click, 300);

                setTimeout(function () {
                    clearInterval(ss);
                }, 3000);
            }

        </script>
    </head>
    <body>
        <input type="button" value="随机点名1（servlet响应）" onclick="random1()"/>&nbsp;&nbsp;${studentName}
        <br/>
        <br/>
        <input type="button" value="随机点名2（ajax响应）" onclick="random2()"/>&nbsp;&nbsp;<span id="showName"></span>
        <br/>
        <br/>
        <input type="button" value="随机点名3（纯js响应）" onclick="random3()"/>&nbsp;&nbsp;<span id="show_name"></span>
        <br/>
        <br/>
        <input type="button" value="开始点名" onclick="startRoleCall()">
        <br/>
        <%--style="background-color: cornflowerblue--%>
        <table border="1" width="100%">
            <tr>
                <th class="c1" id="s0">樊书琪</th>
                <th class="c1" id="s1">杨治宇</th>
                <th class="c1" id="s2">毛晓睿</th>
                <th class="c1" id="s3">许文韬</th>
                <th class="c1" id="s4">杨争鸣</th>
                <th class="c1" id="s5">高超</th>
            </tr>
        </table>
        <br/>
        <br/>
        <input type="button" value="击鼓传花" onclick="clickFlower()">
        <br/>
        <%--style="background-color: cornflowerblue--%>
        <table border="1" width="100%">
            <tr>
                <th class="c2" id="t0">樊书琪</th>
                <th class="c2" id="t1">杨治宇</th>
                <th class="c2" id="t2">毛晓睿</th>
                <th class="c2" id="t3">许文韬</th>
                <th class="c2" id="t4">杨争鸣</th>
                <th class="c2" id="t5">高超</th>
            </tr>
        </table>

    </body>
</html>