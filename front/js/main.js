$(document).ready(function() {
  chamar_cad();
  chamar_login();

  const formulario = document.getElementById("loginForm");
  const emailInput = document.getElementById("email");
  const senhaInput = document.getElementById("senha");

  formulario.addEventListener('submit', function(event) {
      event.preventDefault();
      logar();
  });

  function logar() {
      fetch("http://localhost:8080/usuarios/login", {
          headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
          },
          method: "POST",
          body: JSON.stringify({
              email: emailInput.value,
              senha: senhaInput.value
          })
      })
      .then(res => {
          if (res.ok) {
              return res.json();
          } else {
              return res.text().then(text => { throw new Error(text) });
          }
      })
      .then(data => {
          console.log("Login bem-sucedido");
          window.location.href = "main.html";
      })
      .catch(err => {
          console.error("Erro: ", err);
          alert(err.message); // Mostra a mensagem de erro
      });
  }

  $("#cadastro").click(function() {
      cadastrar();
  });

  function cadastrar() {
      const nome = $("#nome").val();
      const sobrenome = $("#sobrenome").val();
      const email = $("#cadEmail").val();
      const senha = $("#cadSenha").val();
      const senhaConfirm = $("#cadSenhaConfirm").val();
      const regiao = $("#regiao").val();
      const telefone = $("#telefone").val();

      if (senha !== senhaConfirm) {
          alert("As senhas não correspondem");
          return;
      }

      const nomeCompleto = nome + " " + sobrenome;

      fetch("http://localhost:8080/usuarios/registrar", {
          headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
          },
          method: "POST",
          body: JSON.stringify({
              nome: nomeCompleto,
              email: email,
              senha: senha,
              regiao: regiao,
              telefone: telefone
          })
      })
      .then(res => res.json())
      .then(data => {
          console.log("Cadastro bem-sucedido: ", data);
          window.location.href = "main.html";
      })
      .catch(err => console.log("Erro: ", err));
  }
});

function chamar_cad(){
  $("#cad").click(function (){ 
      $("#direita").fadeIn("fast");
      $("#img_esquerda").fadeIn("fast");
      $("#esquerda").slideUp(1500);
      $("#img_direita").slideUp(1500);
  });
}

function chamar_login(){
  $("#login").click(function (){ 
      $("#esquerda").slideDown(1500);
      $("#img_direita").slideDown(1500);
      $("#direita").fadeOut("fast");
      $("#img_esquerda").fadeOut("fast");
  });
}
