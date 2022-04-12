<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/indexContent.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
    <div class="div-content">
        <div class="dataApi-wrap row">
            <div class="dataApi-title">
                <div>일일 코로나 현황 집계<button id="changeMode" type="button" class="btn btn-outline-primary">확진자</button></div>
            </div>
            
            <hr>
            <div class="dataApi-today col-lg-2">
                당일
                <div></div>
            </div>
            <div class="dataApi-today col-lg-2">
                누적
                <div></div>
            </div>
            <div class="dataApi-chart col-lg-8">                
                <ul>
                </ul>
            </div>
        </div>
        <div class="card border-primary mb-3" style="width: 100%">
            <div class="card-header">님의 코로나 경험담을 들려주세요!
                <span class="material-icons">close</span>
            </div>
            <div class="card-body row">
                <div class="card-left col-lg-4">
                    <h4 class="card-title">증상</h4>
                    <p class="card-text">무증상일 경우 넘어가세요</p>
                    <label><input type="checkbox" name="symptom" value="1">근육통</label>
                    <label><input type="checkbox" name="symptom" value="2">인후통</label>
                    <label><input type="checkbox" name="symptom" value="3">두통</label>
                    <label><input type="checkbox" name="symptom" value="4">열</label>
                    <label><input type="checkbox" name="symptom" value="5">기침/가래/콧물</label>
                    <label><input type="checkbox" name="symptom" value="6">후각/미각상실</label>
                </div>
                <div class="card-right col-lg-4">
                    <h4 class="card-title">확진정보</h4>
                    <p class="card-text">무증상일 경우 증상 발현일은 확진일과 똑같이 설정해 주세요</p>
                    <input type="date" name="painDate">
                    <input type="date" name="decideDate">
                    <select>
                        <option>백신 접종여부</option>
                        <option value="0">미접종</option>
                        <option value="1">1차</option>
                        <option value="2">2차</option>
                        <option value="3">3차</option>
                    </select>
                </div>
                <div class="card-right col-lg-4">
                    <h4 class="card-title">나의 격리법</h4>
                    <p class="card-text">격리생활을 어떻게 버텼나요? 격리생활 이야기를 나눠주세요</p>
                    <textarea name="dd"></textarea>
                    <input type="submit" value="제출하기" class="btn btn-primary">
                </div>

            </div>
        </div>
    </div>
    <script>
        // 카드 섹션
        $(".card-header").on("click",function(){
            if($(this).next().css("display")=="none"){
                $(this).next().slideDown();
            }else{
                $(this).next().slideUp();
            }
        })        
        $(".card-header>span").on("click",function(){
            $(this).parent().parent().hide();
        })

        // 로딩시
        const rateArray = new Array();
        chartReset(0);

        $("#changeMode").on("click",function(){
            if($(this).text()=="사망자"){
                chartReset(0);
                $(this).text("확진자");
                
            }else{
                chartReset(1);
                $(this).text("사망자");                    
            }         
        })

        function chartReset(input){
            $("div.dataApi-chart>ul").empty();
            $.ajax({
                url : "/coronaLiveData.do",
                type : "get",
                success : function(data){
                    let todayDecided = 0;
                    let sumDecided = 0;
                    if(input==0){
                        todayDecided = data[0].checkCount-data[1].checkCount;
                        sumDecided = data[0].checkCount;
                    }else{
                        todayDecided = data[0].checkDeath-data[1].checkDeath;
                        sumDecided = data[0].checkDeath;
                    }

                    $(".dataApi-today>div").eq(0).text(Number(todayDecided).toLocaleString());
                    $(".dataApi-today>div").eq(1).text(Number(sumDecided).toLocaleString());

                    // 한바퀴 돌고나면 최고점 기준으로 수치 셋팅
                    for(let i=data.length-1;i>=0;i--){
                    const li = $("<li>");
                    const num = $("<span class='chart-col-num'>");
                        if(input==0){
                            num.text(Number(data[i].checkCount).toLocaleString());
                        }else{
                            num.text(Number(data[i].checkDeath).toLocaleString());
                        }
                    const rateDiv = $("<div class='chart-col-rate deathCnt' style='height:0px;'>");
                        let rate = 0;
                        if(i==6){
                            rate = 10;
                        }else{
                            if(input==0){
                                rate = (data[i].checkCount-data[6].checkCount) / (data[0].checkCount - data[6].checkCount) * 150;
                            }else{
                                rate = (data[i].checkDeath-data[6].checkDeath) / (data[0].checkDeath - data[6].checkDeath) * 150;
                            }                            
                        }
                    rateArray.push(rate);
                    const name = $("<div class='chart-col-name'>");
                    name.text(data[i].checkDate);
                    
                    num.appendTo(li);
                    rateDiv.appendTo(li);
                    name.appendTo(li);       

                    li.appendTo("div.dataApi-chart>ul");
                    } // for문 종료

                    $(".chart-col-rate").on("mouseover",function(){
                        $(this).prev().css("font-weight","900");
                    });
                    $(".chart-col-rate").on("mouseleave",function(){
                        $(this).prev().css("font-weight","400");
                    });
                },
                error : function(){
                    console.log("에러");
                },
                complete : function(){
                    $(".chart-col-rate").each(function(index, item){
                        $(item).css("height", rateArray[index]);
                    })
                }
            })
        }

    </script>
    <!-- 본문영역 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>