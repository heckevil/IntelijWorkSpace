var cmtFrmElem = document.querySelector('#cmtFrm');
var cmtListElem = document.querySelector('#cmtList');
var cmtModModalElem = document.querySelector('#modal');

function regCmt() {
    var cmtVal = cmtFrmElem.cmt.value;
    var param = {
        iboard: cmtListElem.dataset.iboard,
        cmt: cmtVal
    };
    regAjax(param);
}

//서버에게 등록해줘~~~
function regAjax(param) {
    const init = {
        method: 'POST',
        body: JSON.stringify(param),
        headers:{
            'accept' : 'application/json',
            'content-type' : 'application/json;charset=UTF-8'
        }
    };

    fetch('cmt', init)
        .then(function(res) {
            return res.json();
        })
        .then(function(myJson) {
            console.log(myJson);

            switch(myJson.result) {
                case 0: //등록 실패
                    alert('등록 실패!');
                    break;
                case 1: //등록 성공
                    cmtFrmElem.cmt.value = '';
                    getListAjax();
                    break;
            }
        });
}

//서버에게 댓글 리스트 자료 달라고 요청하는 함수
function getListAjax() {
    var iboard = cmtListElem.dataset.iboard;
    /* 쿼리 안보내는 restfull style */
    fetch('cmt/' + iboard)
        .then(function(res) {
            return res.json();
        })
        .then(function(myJson) {
            console.log(myJson);

            makeCmtElemList(myJson);
        });
}

function makeCmtElemList(data) {

    cmtListElem.innerHTML = '';

    var tableElem = document.createElement('table');
    var trElemTitle = document.createElement('tr');
    var thElemCtnt = document.createElement('th');
    var thElemWriter = document.createElement('th');
    var thElemRegdate = document.createElement('th');
    var thElemBigo = document.createElement('th');

    thElemCtnt.innerText = '내용';
    thElemWriter.innerText = '작성자';
    thElemRegdate.innerText = '작성일';
    thElemBigo.innerText = '비고';

    trElemTitle.append(thElemCtnt);
    trElemTitle.append(thElemWriter);
    trElemTitle.append(thElemRegdate);
    trElemTitle.append(thElemBigo);

    tableElem.append(trElemTitle);
    cmtListElem.append(tableElem);

    var loginUserPk = cmtListElem.dataset.loginUserPk;

    data.forEach(function(item) {
        var trElemCtnt = document.createElement('tr');
        var tdElem1 = document.createElement('td');
        var tdElem2 = document.createElement('td');
        var tdElem3 = document.createElement('td');
        var tdElem4 = document.createElement('td');

        tdElem1.innerText = item.cmt;
        tdElem2.append(item.writerNm);
        tdElem3.append(item.regdate);

        if(parseInt(loginUserPk) === item.iuser) {
            var delBtn = document.createElement('button');
            var modBtn = document.createElement('button');

            //삭제버튼 클릭시
            delBtn.addEventListener('click', function() {
                /* String > false = empty, ture = not empty */
                // Int > false = 0 , true = not 0
                if(confirm('삭제하시겠습니까?')) {
                    /* confirm if 문 안에 있으니 boolean형태로 리턴 */
                    delAjax(item.icmt);
                }
            });

            //수정버튼 클릭시
            modBtn.addEventListener('click', function() {
                //댓글 수정 모달창 띄우기
                openModModal(item);
            });

            delBtn.innerText = '삭제';
            modBtn.innerText = '수정';

            tdElem4.append(delBtn);
            tdElem4.append(modBtn);
        }

        trElemCtnt.append(tdElem1);
        trElemCtnt.append(tdElem2);
        trElemCtnt.append(tdElem3);
        trElemCtnt.append(tdElem4);

        tableElem.append(trElemCtnt);
    });
}

function delAjax(icmt) {
    fetch('cmt/' + icmt,{method:'DELETE'})
        .then(function(res) {
            return res.json();
        })
        .then(function(data) {
            console.log(data);


            switch(data.result) {
                case 0:
                    alert('댓글 삭제를 실패하였습니다.');
                    break;
                case 1:
                    getListAjax();
                    break;
            }
        });
}

function modAjax() {
    var cmtModFrmElem = document.querySelector('#cmtModFrm');
    var param = {
        icmt: cmtModFrmElem.icmt.value,
        cmt: cmtModFrmElem.modCmt.value
    }

    const init = {
        method: 'PUT',
        body: JSON.stringify(param),
        headers: {
            'accept' : 'application/json',
            'content-type':'application/json;charset=UTF-8'
        }
    };
    /* Json형태로 보낼때 body에 JSON.stringify(param) 널고 받을때
       controller 파라미터안 @Requestbody로 받음 */


    fetch('cmt', init)
        .then(function(res) {
            return res.json();
        })
        .then(function(myJson) {
            closeModModal();
            switch(myJson.result) {
                case 0:
                    alert('댓글 수정을 실패하였습니다.');
                    break;
                case 1:
                    getListAjax();
                    break;
            }
        });
}

function openModModal({icmt, cmt}) {
    cmtModModalElem.className = '';

    var cmtModFrmElem = document.querySelector('#cmtModFrm');
    cmtModFrmElem.icmt.value = icmt;
    cmtModFrmElem.modCmt.value = cmt;
}

function closeModModal() {
    cmtModModalElem.className = 'displayNone';
}

getListAjax();