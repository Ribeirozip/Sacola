  const formCadastrarLoja = document.getElementById('form-cadastrar-loja');
    const listaLojas = document.getElementById('lista-lojas');

    formCadastrarLoja.addEventListener('submit', async (event) => {
        event.preventDefault();

        const nome = formCadastrarLoja.querySelector('#nome').value;
        const endereco = formCadastrarLoja.querySelector('#endereco').value;

        try {
            const response = await fetch('http://localhost:8080/usuarios/1/lojas', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    nome,
                    endereco
                })
            });

            const data = await response.json();

            if (response.ok) {
                alert('Loja cadastrada com sucesso!');
                formCadastrarLoja.reset();
                fetchLojas();
            } else {
                alert('Erro ao cadastrar loja: ' + data.error);
            }
        } catch (error) {
            console.error('Erro:', error);
            alert('Erro ao cadastrar loja. Verifique a conexão com o servidor.');
        }
    });

    async function fetchLojas() {
        try {
            const response = await fetch('http://localhost:8080/lojas/listar');
            const data = await response.json();

            if (response.ok) {
                renderLojas(data);
            } else {
                alert('Erro ao obter lista de lojas: ' + data.error);
            }
        } catch (error) {
            console.error('Erro:', error);
            alert('Erro ao obter lista de lojas. Verifique a conexão com o servidor.');
        }
    }

    function renderLojas(lojas) {
        listaLojas.innerHTML = '';

        lojas.forEach(loja => {
            const lojaElement = document.createElement('div');
            lojaElement.classList.add('loja');

            lojaElement.innerHTML = `
                <h3>${loja.nome}</h3>
                <p><strong>Endereço:</strong> ${loja.endereco}</p>
            `;

            listaLojas.appendChild(lojaElement);
        });
    }

    // Carregar as lojas ao carregar a página
    document.addEventListener('DOMContentLoaded', () => {
        fetchLojas();
    });

