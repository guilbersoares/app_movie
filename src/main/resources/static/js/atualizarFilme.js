$(document).ready(function () {
    // Obtém a parte final da URL, que deve conter o ID do filme
    var url = window.location.href;
    var partesDaURL = url.split('/');
    var idDoFilme = partesDaURL[partesDaURL.length - 1];

    // Verifica se o ID do filme é um número antes de utilizá-lo
    if (!isNaN(idDoFilme)) {
        // Restante do seu código...

        function atualizarFilme(id, filme) {
            $.ajax({
                url: 'http://localhost:8080/filmes/atualizar/' + id,
                method: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(filme),
                success: function (data) {
                    alert('Filme atualizado na API com sucesso!');
                    $('#titulo, #sinopse, #genero, #ano').val('');
                    // Recarrega a lista de filmes
                    //carregarFilmes();
                },
                error: function (xhr, status, error) {
                    console.error(xhr.responseText);
                    alert('Erro ao atualizar o filme na API. Consulte o console para obter mais informações.');
                }
            });
        }

        // Restante do seu código...

        $('#formFilme').submit(function (event) {
            event.preventDefault();
            let titulo = $('#titulo').val().trim();
            let sinopse = $('#sinopse').val().trim();
            let genero = $('#genero').val().trim();
            let ano = $('#ano').val().trim();

            if (!titulo || !sinopse || !genero || !ano) {
                alert('Por favor, preencha todos os campos do filme.');
                return;
            }

            let filmeAtualizado = {
                titulo: titulo,
                sinopse: sinopse,
                genero: genero,
                ano: ano
            };

            atualizarFilme(idDoFilme, filmeAtualizado);
        });
    } else {
        // Manipule o caso em que o ID do filme não é um número válido
        console.error('ID do filme inválido:', idDoFilme);
    }
});


   