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
       pageHtml += "<button type='button' class='page-link' disabled>";
    } else {
       pageHtml += "<button type='button'  class='page-link' style='cursor: pointer;' onclick='movePage("+ first +")'>";
    }
    pageHtml += "<span class='hidden'>first page</span>";
    pageHtml += "</button>";
    pageHtml += "</li>"

    //prev
    const prev = parseInt(paging.currentPage) -1;
    pageHtml += "<li class='paginate_button page-item prev'>";

    if (paging.firstPage === paging.currentPage) {
       pageHtml += "<button type='button'  class='page-link' disabled>";
    } else {
       pageHtml += "<button type='button'  class='page-link' style='cursor: pointer;' onclick='movePage("+ prev +")'>";
    }
    pageHtml += "<span class='hidden'>prev page</span>";
    pageHtml += "</button>";
    pageHtml += "</li>";

    //① ~ ⑩
    for (let i = paging.firstPageNo; i <= paging.lastPageNo; i++) {
       pageHtml += "<li class='paginate_button page-item";
       //현재 페이지가 인덱스와 같으면 active class 추가
       if (paging.currentPage === i) {
          pageHtml += " active'>";
          pageHtml += "<button type='button'  class='page-link' style='cursor: pointer;' onclick='movePage("+ i +")'>";
          pageHtml += i;
          pageHtml += "</button>";
          pageHtml += "</li>"
       } else {
          pageHtml += "'>";
          pageHtml += "<button type='button'  class='page-link' style='cursor: pointer;' onclick='movePage("+ i +")'>";
          pageHtml += i;
          pageHtml += "</button>";
          pageHtml += "</li>"
       }
    }

    //next
    const next = parseInt(paging.currentPage) + 1;
    pageHtml += "<li class='paginate_button page-item next'>";

    if (paging.currentPage === paging.lastPage) {
       pageHtml += "<button type='button'  class='page-link' disabled>";
    } else {
       pageHtml += "<button type='button'  class='page-link' style='cursor: pointer;' onclick='movePage("+ next +")'>";
    }
    pageHtml += "<span class='hidden'>next page</span>";
    pageHtml += "</button>";
    pageHtml += "</li>"

    //last
    const last = parseInt(paging.lastPage);
    pageHtml += "<li class='paginate_button page-item last'>";

    if (paging.currentPage === paging.lastPage) {
       pageHtml += "<button type='button'  class='page-link' disabled>";
    } else {
       pageHtml += "<button type='button'  class='page-link' style='cursor: pointer;' onclick='movePage("+ last +")'>";
    }
    pageHtml += "<span class='hidden'>last page</span>";
    pageHtml += "</button>";
    pageHtml += "</li>"

    pageHtml += "</ul>";
    //페이지네이션 영역에 반영
    $(".tbl-paging").html(pageHtml);
}

/**
 * 페이지 이동
 * @param currentPage
 */
function movePage(currentPage){
    (searchData !== "") ? getList(searchData, currentPage, menu) : getList("", currentPage, menu);
}

/**
 * ajax 통신으로 리스트를 조회한다.
 */
function getList(searchParam, currentPage, type){
    console.log("getList!!!!");
    $.ajax({
       url: "/api/" + type +"?currentPage=" + currentPage,
       type:"get",
       data: searchParam,
       success: function (data){
          drawTbody(data, type);
          drawPagination(data.paging);
       },
       error:function(e){
           console.error("AJAX Error:", e);
       }
    });
}