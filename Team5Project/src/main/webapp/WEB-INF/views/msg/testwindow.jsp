<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="modal" style="display: block;" id="msg-modal">
        <div class="modal-dialog" role="document"  style="min-width: 470px;">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Message</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
                <span aria-hidden="true"></span>
              </button>
            </div>
            <div class="modal-body">
              <iframe id="msgBox" src="/gotoReceiveMsg.do" style="width:100%; height:600px;"></iframe>
            </div>
            <div class="modal-footer">
              <button id="writeBtn" type="button" class="btn btn-primary">WRITE</button>
              <input type="hidden" value="<%=m.getMemberId()%>" id="memberId">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
          </div>
        </div>
      </div>
      <script>
      	$("#writeBtn").on("click",function(){
          if($(this).text()=="WRITE"){
            const memberId = $("#memberId").val();
            $("#msgBox").attr("src", "/gotoWriteMsg.do?memberId="+memberId);
            $(this).toggleClass("btn-primary").toggleClass("btn-secondary");
            $(this).text("BACK")
            
          } else {
            history.back($("#msgBox"));            
            $(this).text("WRITE");
            $(this).toggleClass("btn-primary").toggleClass("btn-secondary");
          }
      	})

        $("#msg-modal button[data-bs-dismiss=modal]").on("click",function(){                    
          $("#msg-modal").hide();
        })
      </script>
</body>
</html>