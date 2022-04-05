//static폴더는 정적파일들을 넣으며 자동으로 인식함

let index = {
	init: function() {
		$("#btn-save").on("click", () => { //화살표함수를 쓰는이유는 function(){}가 아닌 this를 바인딩하기위해.
			this.save();
		});
/*		$("#btn-login").on("click", () => { //로그인클릭 이벤트
			this.login();
		});//on함수는 첫번째파라미터는 이벤트 정의, 뒤에 무엇을할건지 정의.*/
	}, //쉼표주의
	save: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			title: $("#title").val(), //타이틀
			content: $("#content").val()//내용
		};
		$.ajax({
			type: "POST",	//메소드타입
			url: "/api/board/", //호출
			data: JSON.stringify(data), //http body데이터
			contentType: "application/json; charset=utf-8", //너가 보낸 body데이터  타입은 이거야 라는뜻
			dataType: "json"//요청을 서버로해서 응답이 왔을 때 기본적으로 모든것이 string문자열임.(생긴게 JSON이라면 )=>javascript오브젝트로 변경.
		}).done(function(resp) {
			alert("글쓰기 완료");
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
		},
		}
index.init();