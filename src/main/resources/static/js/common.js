//공통 객체
const cmmObj = {};
    
    //유효성 검증
    cmmObj.validation =
        function validation(frm) {
        }

    /**
     * 리스트 조회(ajax)
     * @param searchParam
     * @param currentPage
     * @param type
     */
    function getList(searchParam, currentPage, type) {
		// console.log("getList");
        $.ajax({
            url: "/api/" + type +"?currentPage=" + currentPage,
            type:"get",
            //IE 브라우저 사용 안한다는 가정하에 주석처리
            // cache: false,
            data: searchParam,
            success: function (data){
                //draw tbody
                drawTbody(data, type);
                //draw pagination
                // drawPagination(data.paging);
            },
            error:function(e){
            }
        });
    }

    /**
     * tbody를 그린다.
     * @param data
     * @param gubun
     */
    function drawTbody(data, gubun) {
        //drawHtml, //draw 대상 tbody
        let htmlData, targetTbody = "";

        switch (gubun) {
            //board
            case "board":
                targetTbody = "boardBody";
                //foreach start
                for (let i = 0; i < data.list.length; i++) {
                    htmlData += "<tr>";
                    htmlData += "<td>";
                    htmlData += data.list[i].no;
                    htmlData += "</td>";
                    htmlData += "<td>";
                    htmlData += data.list[i].title;
                    htmlData += "</td>";
                    htmlData += "<td>" + data.list[i].content;
                    htmlData += "</td>";
                    htmlData += "<td>" + data.list[i].userName;
                    htmlData += "</td>";
                    htmlData += "<td>" + data.list[i].regDate;
                    htmlData += "</td>";
                    htmlData += "<td>" + data.list[i].modDate;
                    htmlData += "</td>";
                    htmlData += "</tr>";
                }
                // foreach end
            break;

            case "user":
                targetTbody = "userBody";
                //foreach start
                for (let i = 0; i < data.list.length; i++) {
                    // htmlData += "<tr>";
                    // htmlData += "<td>";
                    // htmlData += list[i].no;
                    // htmlData += "</td>";
                    // htmlData += "<td>";
                    // htmlData += list[i].;
                    // htmlData += "</td>";
                    // htmlData += "<td>" + list[i].;
                    // htmlData += "</td>";
                    // htmlData += "<td>" + list[i].;
                    // htmlData += "</td>";
                    // htmlData += "<td>" + list[i].;
                    // htmlData += "</td>";
                    // htmlData += "<td>" + list[i].;
                    // htmlData += "</td>";
                    // htmlData += "<td>" + list[i].;
                    // htmlData += "</td>";
                    // htmlData += "<td>" + list[i].;
                    // htmlData += "</td>";
                    // htmlData += "<td>" + list[i].;
                    // htmlData += "</td>";
                    // htmlData += "</tr>";
                }
                // foreach end
            break;
        }
		
		//tbody에 반영
		$("#" + targetTbody + "").html(htmlData);
    }

/****************************** pagination draw start *********************************************/
/**
 * 페이지네이션을 그린다.
 * @param paging
 */
function drawPagination(paging) {

	let pageHtml = "";
	pageHtml += "<ul class='pagination'>";

	//first
	const first = parseInt(paging.firstPage);
	pageHtml += "<li class='paginate_button page-item first'>";

	if (paging.currentPage === paging.firstPage) {
		pageHtml += "<button class='page-link' disabled>";
	} else {
		pageHtml += "<button class='page-link' style='cursor: pointer;' onclick='movePage("+ first +")'>";
	}
	pageHtml += "<span class='hidden'>first page</span>";
	pageHtml += "</button>";
	pageHtml += "</li>"

	//prev
	const prev = parseInt(paging.currentPage) -1;
	pageHtml += "<li class='paginate_button page-item prev'>";

	if (paging.firstPage === paging.currentPage) {
		pageHtml += "<button class='page-link' disabled>";
	} else {
		pageHtml += "<button class='page-link' style='cursor: pointer;' onclick='movePage("+ prev +")'>";
	}
	pageHtml += "<span class='hidden'>prev page</span>";
	pageHtml += "</button>";
	pageHtml += "</li>"

	//① ~ ⑩
	for (let i = paging.firstPageNo; i <= paging.lastPageNo; i++) {
		pageHtml += "<li class='paginate_button page-item";
		//현재 페이지가 인덱스와 같으면 active class 추가
		if (paging.currentPage === i) {
			pageHtml += " active'>";
			pageHtml += "<button class='page-link' style='cursor: pointer;' onclick='movePage("+ i +")'>";
			pageHtml += i;
			pageHtml += "</button>";
			pageHtml += "</li>"
		} else {
			pageHtml += "'>";
			pageHtml += "<button class='page-link' style='cursor: pointer;' onclick='movePage("+ i +")'>";
			pageHtml += i;
			pageHtml += "</button>";
			pageHtml += "</li>"
		}
	}

	//next
	const next = parseInt(paging.currentPage) + 1;
	pageHtml += "<li class='paginate_button page-item next'>";

	if (paging.currentPage === paging.lastPage) {
		pageHtml += "<button class='page-link' disabled>";
	} else {
		pageHtml += "<button class='page-link' style='cursor: pointer;' onclick='movePage("+ next +")'>";
	}
	pageHtml += "<span class='hidden'>last page</span>";
	pageHtml += "</button>";
	pageHtml += "</li>"

	//last
	const last = parseInt(paging.lastPage);
	pageHtml += "<li class='paginate_button page-item last'>";

	if (paging.currentPage === paging.lastPage) {
		pageHtml += "<button class='page-link' disabled>";
	} else {
		pageHtml += "<button class='page-link' style='cursor: pointer;' onclick='movePage("+ last +")'>";
	}
	pageHtml += "<span class='hidden'>last page</span>";
	pageHtml += "</button>";
	pageHtml += "</li>"

	pageHtml += "</ul>";
	//페이지네이션 영역에 반영
	$(".pagiNav").html(pageHtml);
}
/****************************** pagination draw end ***********************************************/







    
    
    
    
    
    
    
    
