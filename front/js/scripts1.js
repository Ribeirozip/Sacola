document.addEventListener('DOMContentLoaded', () => {
    const formCriarProduto = document.getElementById('form-criar-produto');
    const listaProdutosContainer = document.getElementById('lista-produtos-container');

    async function fetchProdutosDaLoja() {
        try {
            const response = await fetch('http://localhost:8080/lojas/listar');
            const data = await response.json();

            if (response.ok) {
                renderProdutos(data);
            } else {
                alert('Erro ao obter lista de produtos da loja: ' + data.message);
            }
        } catch (error) {
            console.error('Erro:', error);
            alert('Erro ao obter lista de produtos da loja. Verifique a conexão com o servidor.');
        }
    }

    function renderProdutos(produtosDaLoja) {
        listaProdutosContainer.innerHTML = '';

        produtosDaLoja.forEach(loja => {
            loja.produtos.forEach(produto => {
                const cardProduto = document.createElement('div');
                cardProduto.classList.add('produto-item');

                cardProduto.innerHTML = `
                    <h3>${produto.nome}</h3>
                    <p><strong>Preço:</strong> R$ ${produto.preco.toFixed(2)}</p>
                    <p><strong>Descrição:</strong> ${produto.descricao}</p>
                    <button data-id="${produto.id}" class="btn-excluir-produto">Excluir</button>
                `;

                cardProduto.querySelector('.btn-excluir-produto').addEventListener('click', async () => {
                    const idProduto = produto.id;
                    if (confirm('Tem certeza que deseja excluir este produto?')) {
                        await excluirProduto(idProduto); // Chama a função para excluir o produto
                    }
                });

                listaProdutosContainer.appendChild(cardProduto);
            });
        });
    }

    async function excluirProduto(idProduto) {
        try {
            const response = await fetch(`http://localhost:8080/produtos/${idProduto}`, {
                method: 'DELETE'
            });

            const data = await response.json(); // Converte a resposta para objeto JavaScript

            if (response.ok) {
                alert('Produto excluído com sucesso!');
                fetchProdutosDaLoja(); // Atualiza a lista de produtos após a exclusão
            } else {
                alert('Erro ao excluir produto: ' + data.message);
            }
        } catch (error) {
            console.error('Erro:', error);
            alert('Erro ao excluir produto. Verifique a conexão com o servidor.');
        }
    }

    formCriarProduto.addEventListener('submit', async (event) => {
        event.preventDefault();

        const nome = formCriarProduto.querySelector('#nome').value;
        const preco = parseFloat(formCriarProduto.querySelector('#preco').value);
        const descricao = formCriarProduto.querySelector('#descricao').value;

        const novoProduto = {
            nome,
            preco,
            descricao
        };

        try {
            const response = await fetch('http://localhost:8080/lojas/1/produtos', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(novoProduto)
            });

            const data = await response.json(); // Converte a resposta para objeto JavaScript

            if (response.ok) {
                alert('Produto criado com sucesso!');
                formCriarProduto.reset();
                fetchProdutosDaLoja(); // Atualiza a lista de produtos após a criação
            } else {
                alert('Erro ao criar produto: ' + data.message);
            }
        } catch (error) {
            console.error('Erro:', error);
            alert('Erro ao criar produto. Verifique a conexão com o servidor.');
        }
    });

    fetchProdutosDaLoja(); // Chama a função para buscar e exibir os produtos da loja ao carregar a página
});
