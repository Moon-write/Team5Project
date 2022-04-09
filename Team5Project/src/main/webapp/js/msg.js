// 전체선택 설정기능
$("#checkboxCol>span").on("click",function(){
    if($(".msg-content>li>input").prop("checked")==true){
        $(".msg-content>li>input").prop("checked", false);	
    }else{
        $(".msg-content>li>input").prop("checked", true);
    }			
});

// 이름클릭하면 쪽지보이는 기능
$(".nameWrap").on("click",function(e){
    const box = $(this).next();
    const xPos = e.offsetX;
    const yPos = e.offsetY;

    box.css("left",e.offsetX);
    box.css("top",e.offsetY);
    
    box.toggle();	
});

$(".writePopup").on("click",function(){
    const memberId = $("#memberHidden").val();
    const receiver = $(this).prev().children().next().text();
    
    location.href="/gotoWriteMsg.do?memberId="+memberId+"&msgReceiver="+receiver;
});

// 뒤로가기버튼
function gotoBack(){
    location.href = document.referrer;
};
