
function chamar_cad(){
    $( "#cad" ).click(function (){ 
        $("#direita").fadeIn("fast");
        $("#img_esquerda").fadeIn("fast");
        $("#esquerda").slideUp(1500);
        $("#img_direita").slideUp(1500);
    });
}
function chamar_login(){
    $( "#login" ).click(function (){ 
        $("#esquerda").slideDown(1500);
        $("#img_direita").slideDown(1500);
        $("#direita").fadeOut("fast");
        $("#img_esquerda").fadeOut("fast");
    });
}
//login
const formulario = document.querySelector("form");
const botao = document.querySelector(".bt_login bt1");
const iemail = document.querySelector(".email");
const isenha = document.querySelector(".password");

function logar(){
  fetch("http://localhost:8080/usuarios/login",
        {
          headers: {
          'Accept':'application/json',
          'Content-Type':'application/json'
          },
    method:"POST",
    body: JSON.stringify({
    email:iemail.value,
    senha:isenha.value
    })
})
.then(function (res) {console.log(res)})
.catch(function (res) {console.log(res)})
};
function limpar() {
  iemail.value = "";
  isenha.value = "";
};

formulario.addEventListener('submit', function (event) {
  event.preventDefault();

  logar();
  limpar();

});