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
                <div>일일 코로나 현황 집계<button id="changeMode" type="button" class="btn btn-outline-secondary">확진자</button></div>
            </div>
            
            <hr>
            <div class="dataApi-today col-lg-4 row">
                <div class="col-sm-6" id="todayCnt">
                    당일
                    <div></div>
                </div>
                <div class="col-sm-6" id="stackCnt">
                    누적
                    <div></div>
                </div>
            </div>
            <div id="dataChart" class="dataApi-chart col-lg-8">                
                <ul>
                </ul>
            </div>
        </div>
        <% if(m!=null&&m.getSurveyCheck()==0) {%>
        <form action="/survey.do" method="post">
            <div class="card border-primary mb-3" style="width: 100%">
                <div class="card-header"><%= m.getMemberNickname() %> 님의 코로나 경험담을 들려주세요!
                    <input type="hidden" name="surveyId" value="<%=m.getMemberId()%>">
                    <span class="material-icons">close</span>
                </div>
                <div class="card-body row">
                    <div class="card-col col-lg-4">
                        <h4 class="card-title">증상</h4>
                        <p class="card-text"></p>
                        <table>
                            <tr>
                                <td><label><input class="form-check-input" type="checkbox" name="symptom" value="1">근육통</label></td>
                                <td><label><input class="form-check-input" type="checkbox" name="symptom" value="2">인후통</label></td>
                                <td><label><input class="form-check-input" type="checkbox" name="symptom" value="3">두통</label></td>
                            </tr>
                            <tr>
                                <td><label><input class="form-check-input" type="checkbox" name="symptom" value="4">열</label></td>
                                <td><label><input class="form-check-input" type="checkbox" name="symptom" value="5">기침/가래/콧물</label></td>
                                <td><label><input class="form-check-input" type="checkbox" name="symptom" value="6">후각/미각상실</label></td>
                            </tr>
                            <tr>
                                <td colspan="3"><label><input class="form-check-input" type="checkbox" name="symptom" value="7">무증상</label></td>
                            </tr>
                        </table>
                    </div>
                    <div class="card-col col-lg-4">
                        <h4 class="card-title">확진정보</h4>
                        <p class="card-text">무증상일 경우 증상 발생일은 확진일과 똑같이 설정해 주세요.</p>
                        <table>
                            <tr>
                                <td><span class="material-icons">coronavirus</span>증상 발생일</td>
                                <td><input type="date" name="painDate" class="form-control"></td>
                            </tr>
                            <tr>
                                <td><span class="material-icons">local_hospital</span>확진 통지일</td>
                                <td><input type="date" name="decideDate" class="form-control"></td>
                            </tr>
                            <tr>
                                <td><span class="material-icons">vaccines</span>백신 접종여부</td>
                                <td>
                                    <select class="form-select" name="vaccinate">
                                        <option>백신 접종여부</option>
                                        <option value="0">미접종</option>
                                        <option value="1">1차</option>
                                        <option value="2">2차</option>
                                        <option value="3">3차</option>
                                    </select>
                                </td>
                            </tr>
                        </table>          
                    </div>
                    <div class="card-col col-lg-4">
                        <h4 class="card-title">나의 격리법</h4>
                        <p class="card-text">격리생활을 어떻게 버텼나요? 격리생활 이야기를 나눠주세요.</p>
                        <textarea name="story" class="form-control"></textarea>
                        <input type="submit" value="제출하기" class="btn btn-primary">
                    </div>
                </div>
            </div>
        </form>
        <% } %>
        <div class="result-wrap row">
            <div class="result-title">
                <div>위드코로나 회원들의 코로나 빅데이터</div>
            </div>
            <hr>
            <div class="result-pain col-lg-4">
                <div class="result-pain-title">회원의 OO%가 이 증상을 경험했어요!</div>
                <table>
                    <tr>
                        <th>근육통</th>
                        <th>인후통</th>
                        <th>두통</th>
                    </tr>
                    <tr>
                        <td><div id="muscle" class="result-rate">10%</div></td>
                        <td><div id="throat" class="result-rate">10%</div></td>
                        <td><div id="head" class="result-rate">10%</div></td>
                    </tr>
                    <tr>
                        <th>열</th>
                        <th>기침/가래/콧물</th>
                        <th>후각/미각상실</th>
                    </tr>
                    <tr>
                        <td><div id="fever" class="result-rate">10%</div></td>
                        <td><div id="cold" class="result-rate">10%</div></td>
                        <td><div id="notaste" class="result-rate">10%</div></td>
                    </tr>
                    <tr>
                        <th colspan="3">무증상</th>
                    </tr>
                    <tr>
                        <td></td>
                        <td><div id="nothing" class="result-rate-last">10%</div></td>
                        <td></td>
                    </tr>
                </table>
            </div>
            <div class="result-pain col-lg-4">
                <div id="result-pain-date">
                    <div class="result-pain-title">평균 발병기간</div>
                    <div>증상이 나타난 이후로<br>
                        <span id="dateGap"></span>일 째에 확진받았어요!</div>
                </div>
                <div id="result-pain-date">
                    <div class="result-pain-title">백신 접종 현황</div>
                    <ul id="vaccineChart">
                        <li><div></div><span>미접종</span></li>
                        <li><div></div><span>1차</span></li>
                        <li><div></div><span>2차</span></li>
                        <li><div></div><span>3차</span></li>
                    </ul>
                </div>
            </div>
        
            <div class="result-pain col-lg-4">
                <div class="result-pain-title">격리생활 어떻게 보냈나요?</div>
                <ul id="bubbleTap">
                    <span class="material-icons" id="moreBtn" value="1">expand_circle_down</span>
                </ul>
                
            </div>
        </div>
    </div>
    <script>
        // 로딩시
        const rateArray = new Array();
        chartReset(0);
        $(".card-header").next().hide();
        readBubble(1);

        $("#changeMode").on("click",function(){
            if($(this).text()=="사망자"){
                chartReset(0);
                $(this).text("확진자");
                
            }else{
                chartReset(1);
                $(this).text("사망자");                    
            }         
        })

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

        $("input[name=symptom][value=7]").on("click",function(){
            if($(this).prop("checked")==true){
                $("input[name=symptom]").not("[value=7]").prop("checked", false);
                $("input[name=symptom]").not("[value=7]").prop("disabled", true);
            }else{
                $("input[name=symptom]").not("[value=7]").prop("disabled", false);
            }
        })    
        
        $("#moreBtn").on("mouseover",function(){
            $(this).css("color","#d4c3de");
        });
        $("#moreBtn").on("mouseleave",function(){
            $(this).css("color","#828e95");
        });

        $("#moreBtn").on("click",function(){
            readBubble($(this).val());
        });
    


        function chartReset(input){
            $("div.dataApi-chart>ul").empty();
            $.ajax({
                url : "/coronaLiveData.do",
                data : { "searchKeyword" : input },
                type : "get",
                success : function(data){
                    const todayDecided = data[0].checkCount-data[1].checkCount;
                    const sumDecided = data[0].checkCount;

                    $("#todayCnt>div").text(Number(todayDecided).toLocaleString());
                    $("#stackCnt>div").text(Number(sumDecided).toLocaleString());

                    // 한바퀴 돌고나면 최고점 기준으로 수치 셋팅
                    for(let i=data.length-1;i>=0;i--){
	                    const li = $("<li>");
	                    const num = $("<span class='chart-col-num'>");
	                        
	                    num.text(Number(data[i].checkCount).toLocaleString());
	                        
	                    const rateDiv = $("<div class='chart-col-rate deathCnt' style='height:0px;'>");
	                        let rate = 0;
	                        if(i==6){
	                            rate = 10;
	                        }else{
	                            rate = (data[i].checkCount-data[6].checkCount) / (data[0].checkCount - data[6].checkCount) * 150;                           
	                        }
	                    rateArray.push(rate);
	                    
	                    const name = $("<div class='chart-col-name'>");
	                    name.text(data[i].checkDate);
	                    
	                    num.appendTo(li);
	                    rateDiv.appendTo(li);
	                    name.appendTo(li);       
	
	                    li.appendTo("div#dataChart>ul");
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

        $.ajax({
            url : "/surveyResult.do",
            type : "post",
            success : function(data){
                $("#muscle").text(Math.round((data.spt1Count/data.totalCount )*100)+"%");
                $("#throat").text(Math.round((data.spt2Count/data.totalCount )*100)+"%");
                $("#head").text(Math.round((data.spt3Count/data.totalCount )*100)+"%");
                $("#fever").text(Math.round((data.spt4Count/data.totalCount )*100)+"%");
                $("#cold").text(Math.round((data.spt5Count/data.totalCount )*100)+"%");
                $("#notaste").text(Math.round((data.spt6Count/data.totalCount )*100)+"%");
                $("#nothing").text(Math.round((data.spt7Count/data.totalCount )*100)+"%");

                $("#dateGap").text(data.dateGap);

                $("#vaccineChart>li>div").eq(0).text(data.vaccine0);
                $("#vaccineChart>li>div").eq(0).css("height", data.vaccine0*10+"px");
                $("#vaccineChart>li>div").eq(1).text(data.vaccine1);
                $("#vaccineChart>li>div").eq(1).css("height", data.vaccine1*10+"px");
                $("#vaccineChart>li>div").eq(2).text(data.vaccine2);
                $("#vaccineChart>li>div").eq(2).css("height", data.vaccine2*10+"px");
                $("#vaccineChart>li>div").eq(3).text(data.vaccine3);
                $("#vaccineChart>li>div").eq(3).css("height", data.vaccine3*10+"px");
            },
            error : function(){
                console.log("문제있다");
            }
        })

        function readBubble(input){
            $.ajax({
                url : "/storyResult.do",
                data : { "readNum": input },
                type : "post",
                success : function(data){
                    for(let i=0;i<data.length;i++){
                        $("<li class='bubble'><span>◀</span>"+data[i]+"</li>").prependTo($("#bubbleTap"));
                    }
                    $("#moreBtn").val(Number(input)+3);
                },
                error : function(){
                    console.log("문제있어");
                }
            });
        };
    </script>
    <!-- 본문영역 -->
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>