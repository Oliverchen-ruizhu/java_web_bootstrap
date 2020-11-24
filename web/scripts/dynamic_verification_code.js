var code=function () {
    var width = 100; //宽度
    var height = 40; //高度
    var fontSize = height-20; //字体大小
    var str = "0123456789qazwsxedcrfvtgbyhnujikolpQAZWSXEDCRFVTGBYHNUJMIKOLP";
    //随机生成最大值不超过max的整数
    function randInt(max) {
        return Math.floor(Math.random()*100000%max);
    }
    //生成随机长度的字符串验证码
    function randCode(lng) {
        if (lng<4){
            lng=4;
        }
        var code = "";
        for (var i=0;i<lng;i++){
            code +=str.charAt(randInt(str.length));
        }
        return code;
    }
    //生成随机颜色
    function randColor() {
        var r = randInt(256);
        var g = randInt(256);
        var b = randInt(256);
        return "rgb("+r+","+g+","+b+")";
    }
    //绘制图片
    function drawCode(canvas) {
        var vailCode = randCode(4);
        width = 5+fontSize*vailCode.length;
        if (canvas !=null && canvas.getContext && canvas.getContext("2d")){
            //设置显示区域大小
            canvas.style.width=width;
            //设置画板高宽
            canvas.setAttribute("width",width);
            canvas.setAttribute("height",height);
            //得到画笔
            var  pen = canvas.getContext("2d");
            //绘制背景
            pen.fillStyle = "rgb(255,255,255)";
            pen.fillRect(0,0,width,height);
            //设置值水平线位置
            pen.textBaseline = "top";
            //绘制内容
            for (var i=0;i<vailCode.length;i++){
                pen.fillStyle = randColor();
                pen.font="blod"+(fontSize+randInt(3))+"px 微软雅黑";
                pen.fillText(vailCode.charAt(i),5+fontSize*i,randInt(5));
            }
            //绘制噪音线
            for (var i=0;i<2;i++){
                pen.moveTo(randInt(width)/2,randInt(height)); //设置起点
                pen.lineTo(randInt(width),randInt(height)); //设置终点
                pen.strokeStyle = randColor();
                pen.lineWidth =2; //线条粗细
                pen.stroke();
            }
            return vailCode;
        }
    }
    var vailCode = null;
    function changeCode() {
        var cvs =
        vailCode = drawCode(cvs);
    }
}();