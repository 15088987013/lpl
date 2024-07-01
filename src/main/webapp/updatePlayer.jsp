<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>添加学生</title>  
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
    <script type="text/javascript">
        $(function (){
            //给按钮绑定事件
            $("#btn").click(function (){
                //发送ajax请求
                $.post("/lplsystem/serve/PlayerServlet",$("form").serialize()+"&method=updatePlayer",function (result){
                    if(result>0){
                        alert("添加成功");
                    }else{
                        alert("添加失败");
                    }
                })
            })
            $("#return").click(function(){

            })
        })

    </script>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span>修改选手</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="">
      <div class="form-group">
        <div class="label">
          <label>姓名：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="pname" value=""  style="width:30%"/>
          <div class="tips"></div>
        </div>
      </div>

	  <div class="form-group">
        <div class="label">
          <label>位置：</label>
        </div>
        <div class="field" style="padding-top:8px;">
            上单 <input   type="radio" name="position" value="上单" />
            打野 <input   type="radio" name="position" value="打野" />
            中单 <input   type="radio" name="position" value="中路" />
            下路 <input   type="radio" name="position" value="下路" />
            辅助 <input   type="radio" name="position" value="辅助" />
        </div>
      </div>

        <div class="form-group">
            <div class="label">
                <label>爱好：</label>
            </div>
            <div class="field" style="padding-top:8px;">
                洗澡<input type="checkbox"  name="hobby" value="洗澡" />
                炒粉 <input type="checkbox" name="hobby"  value="炒粉" />
                德州扑克 <input type="checkbox" name="hobby"  value="德州扑克" />
                炼化骨灰 <input type="checkbox"  name="hobby" value="炼化骨灰" />
                唐宋 <input type="checkbox" name="hobby"  value="唐宋" />
                未知 <input type="checkbox" name="hobby"  value="未知" />

            </div>
        </div>

	   <div class="form-group">
        <div class="label">
          <label>出生日期：</label>
        </div>
        <div class="field">
          <input type="date" class="input" name="birthdate" value=""  style="width:30%"/>
          <div class="tips"></div>
        </div>
      </div>
	  
	   <div class="form-group">
        <div class="label">
          <label>手机号：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="phone" value="" style="width:30%"/>
        </div>
      </div>
	  
	   <div class="form-group">
        <div class="label">
          <label>所在战队：</label>
        </div>
        <div class="field">
        
		  <select class="input" style="width:30%" id="sel" name="tid">
		   <option>--请选择--</option>
		  </select>
		  
        </div>
      </div>

	  <div class="form-group">
        <div class="label">
          <label>个人简介：</label>
        </div>
        <div class="field">
          <textarea type="text" class="input" name="pdesc" style="height:80px;"></textarea>
        </div>
      </div>
	  
    
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="button" id="btn"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>
</body></html>