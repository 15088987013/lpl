<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
  <base href="<%=request.getContextPath()+"/"%>" />
    <title>修改战队</title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
  <script type="text/javascript">
    $(function (){
      $("#btn").click(function(){
        //发送ajax请求
        $.post("/lplsystem/serve/TeamServlet?method=updateTeam",$("form").serialize(),function (result) {
          if(result>0){
            alert("修改成功")
          }else {
            alert("修改失败")
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
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span>修改战队</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="">
<%--      隐藏键值对传递到后端--%>
      <input type="hidden"  name="tid" value="${requestScope.oneteam.tid}"  style="width:30%"/>
      <div class="form-group">
        <div class="label">
          <label>战队名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="tname" value="${requestScope.oneteam.tname}"  style="width:30%"/>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>战队位置：</label>
        </div>
        <div class="field">
          <input type="text" class="input" name="tlocation" value="${requestScope.oneteam.tlocation}" style="width:30%"/>
        </div>
      </div>
	  
	   <div class="form-group">
        <div class="label">
          <label>战队描述：</label>
        </div>
        <div class="field">
          <textarea  class="input" name="desc"  style="height:80px;">${requestScope.oneteam.desc}</textarea>
        </div>
      </div>
	  
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="button" id="btn"> 提交修改</button>
        </div>
      </div>
    </form>
  </div>
</div>
</body></html>