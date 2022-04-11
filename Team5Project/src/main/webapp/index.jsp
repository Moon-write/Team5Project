<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    /* 전국 확진자 현황 API */
    .dataApi-wrap{
        width: 100%;
        height: 270px;
        border: 1px solid #a393ac;
        border-radius: 10px;
        margin-left: 0px !important;
        margin-top: 50px !important;
        align-items: center;
    }
    .dataApi-title{
        text-align: center;
        font-size: 20px;
        font-weight: 900;
        align-self: center;
    }
    .dataApi-today{
        width: 30%;
        text-align: center;
        font-size: 1.1em;
        font-weight: 700;
        justify-content: center;        
    }
    .dataApi-today>div {
        width: 80%; background-color: #D4C3DE;
        border-radius: 10px;
        font-size: 1.5em;
        max-height: 200px;
        padding: 10% 0px;
        margin: 20px auto;
    }
    .dataApi-chart{
        width: 70%;
    }

    .dataApi-chart>ul{
        clear: both;
        list-style-type: none;
        padding: 0px; margin: 0px;
        display: flex;
        justify-content: center;
    }

    .dataApi-chart li{
        margin: 0px 10px;
        height: 150px;
        display: flex;
        width: 40px;
        flex-wrap: wrap;
        align-content: flex-end;
        justify-content: center;
    }
    .dataApi-chart span.chart-col-num{
        font-size: 0.8em;
    }

    .dataApi-chart div.chart-col-rate{
        display: block;
        width: 100%;
        background-color: #D4C3DE;
        border-top-left-radius: 10px;
        border-top-right-radius: 10px;
        align-self: flex-end;
        font-size: 0.6em;
        animation-duration: height 1s;
    }
    .dataApi-chart div.chart-col-name{
        width: 100%;
        display: block;
        text-align: center;
        
        align-self: flex-end;
        font-size: 0.8em;
    }

    /* 설문조사 부분 */
    .card-header{
        position: relative;
        text-align: center;
        cursor: pointer;
    }
    .card-header>span{
        position: absolute;
        top: 10px; right: 10px;
    }
    .card-body{
        display: none;
    }

</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
    <div class="div-content">
        <div class="dataApi-wrap row">
            <div class="dataApi-title col-sm-12">전국 일일 코로나 현황</div>
            <div class="dataApi-today col-sm-5">
                오늘의 확진자
                <div>50000</div>
            </div>
            <div class="dataApi-chart col-sm-7">
                <ul>
                    <li>
                        <span class="chart-col-num">10000</span>
                        <div class="chart-col-rate deathCnt" style="height:10%"></div>
                        <div class="chart-col-name">1</div>
                    </li>
                    <li>
                        <span class="chart-col-num">10000</span>
                        <div class="chart-col-rate deathCnt" style="height:20%"></div>
                        <div class="chart-col-name">2</div>
                    </li>
                    <li>
                        <span class="chart-col-num">10000</span>
                        <div class="chart-col-rate deathCnt" style="height:30%"></div>
                        <div class="chart-col-name">3</div>
                    </li>
                    <li>
                        <span class="chart-col-num">10000</span>
                        <div class="chart-col-rate deathCnt" style="height:40%"></div>
                        <div class="chart-col-name">4</div>
                    </li>
                    <li>
                        <span class="chart-col-num">10000</span>
                        <div class="chart-col-rate deathCnt" style="height:50%"></div>
                        <div class="chart-col-name">5</div>
                    </li>
                    <li>
                        <span class="chart-col-num">10000</span>
                        <div class="chart-col-rate deathCnt" style="height:60%"></div>
                        <div class="chart-col-name">6</div>
                    </li>
                    <li>
                        <span class="chart-col-num">10000</span>
                        <div class="chart-col-rate deathCnt" style="height:70%"></div>
                        <div class="chart-col-name">7</div>
                    </li>
                </ul>
            </div>
        </div>
        <div class="card border-primary mb-3" style="width: 100%">
            <div class="card-header">님의 코로나 경험담을 들려주세요!
                <span class="material-icons">close</span>
            </div>
            <div class="card-body">
                <h4 class="card-title">Primary card title</h4>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            </div>
        </div>
    </div>
    <script>
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
        $.ajax({
            url : "/coronaLiveData.do",
            type : "get",
            success : function(data){
                console.log(data);

            },
            error : function(){
                console.log("에러");
            }
        })
    </script>
    <!-- 본문영역 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>

</body>
</html>