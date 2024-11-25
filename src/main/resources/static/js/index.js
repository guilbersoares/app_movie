$(document).ready(function () {
    // Função para carregar os filmes da API e exibi-los na tabela
    function carregarFilmes() {
        
        $.ajax({
            url: 'http://localhost:8080/filmes/listar',
            method: 'GET',
            success: function (data) {
                
                // Limpa a tabela de filmes
                $('#tabelaFilmes').empty();
                // Adiciona cada filme à tabela
                data.forEach(filme => {
                    let tr = $('<tr>');
                    tr.append('<td>' + filme.id + '</td>');
                    tr.append('<td>' + filme.titulo + '</td>');
                    tr.append('<td>' + filme.sinopse + '</td>');
                    tr.append('<td>' + filme.genero + '</td>');
                    tr.append('<td>' + filme.ano + '</td>');
                    let botaoDeletar = $('<button>')
                        .text('Excluir')
                        .click(function () {
                            deletarFilme(filme.id);
                        });

                    let botaoAtualizar = $('<button>')
                        .text('Atualizar')
                        .click(function () {
                            window.location.href = 'http://localhost:8080/atualizarFilmeForm/' + filme.id;
                        });

                    let tdBotoes = $('<td>').append(botaoAtualizar).append(botaoDeletar);
                    tr.append(tdBotoes);
                    $('#tabelaFilmes').append(tr);
                })
                
            },
            error: function () {
                alert('Não foi possível carregar os filmes da API.');
                
            }
        });
    }

    function deletarFilme(id) {
        $.ajax({
            url: 'http://localhost:8080/filmes/deletar/' + id,
            method: 'DELETE',
            success: function (data) {
                alert('Filme removido na API com sucesso!');
                // Recarrega a lista de filmes
                carregarFilmes();
            },
            error: function () {
                alert('Não foi possível deletar o filme na API.');
            }
        });
    }

    

    // Carrega os filmes ao abrir a página
    carregarFilmes();
});


