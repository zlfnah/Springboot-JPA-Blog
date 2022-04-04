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
			username: $("#username").val(), //아이디값찾기.
			password: $("#password").val(),
			email: $("#email").val()
		};
		//console.log(data);

		//ajax호출시 default가 비동기 호출. //회원가입 수행요청
		//ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청
		//ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌.
		$.ajax({
			type: "POST",	//메소드타입
			url: "/auth/joinProc", //호출
			data: JSON.stringify(data), //http body데이터
			contentType: "application/json; charset=utf-8", //너가 보낸 body데이터  타입은 이거야 라는뜻
			dataType: "json"//요청을 서버로해서 응답이 왔을 때 기본적으로 모든것이 string문자열임.(생긴게 JSON이라면 )=>javascript오브젝트로 변경.
		}).done(function(resp) {
			alert("회원가입이 완료");
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});//ajax통신을 이용해서 3개의 데이터를 json으로변경하여 insert요청!!!
		//Ajax를 사용하는 첫번째 이유 : 고객이 사용하는 클라이언트는 크게 2가지로 웹과 앱이 있으며, 일반적으로 서버로 부터 응답받을때 웹은 HTML파일을 받고 앱은 데이터(JSON)를 받는다. 
		//이것은 서버를 이원화하여 구축한 것을 의미하며 이때 '서버를 통합하여 각각의 클라이언트에게 응답해줄순 없을까?'라는 의문점에서 고안된 방법이 Ajax통신이다. 
		//Ajax통신을 사용하면 웹은 서버로부터 데이터(JSON)를 리턴받을 수 있으며 그렇게 되면 서버의 분리 필요없이 하나의 서버로 각각의 클라이언트 요청을 받아 응답해줄수 있게된다.
		// 대신 웹클라이언트는 추가적인 요청을 통해 HTML파일을 받아야한다.
		//2번째 비동기 통신을 하기 위해서 (비동기 통신=  순서에 상관없는)
	}, //회원가입끝
	//로그인시작
	login: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(), //아이디값찾기.
			password: $("#password").val()
		};
		//console.log(data);

		/*$.ajax({ //security쓸때 이 로그인 로직안씀
			type: "POST",	//메소드타입
			url: "/api/user/login", //호출
			data: JSON.stringify(data), //http body데이터
			contentType: "application/json; charset=utf-8", //너가 보낸 body데이터  타입은 이거야 라는뜻
			dataType: "json"//요청을 서버로해서 응답이 왔을 때 기본적으로 모든것이 string문자열임.(생긴게 JSON이라면 )=>javascript오브젝트로 변경.
		}).done(function(resp) {
			alert("로그인이 완료되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});*/
	}
}

index.init();