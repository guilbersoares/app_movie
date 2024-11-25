$(document).ready(function () {
    function criarFilme(filme) {
        
        $.ajax({
            
            url: 'http://localhost:8080/filmes/adicionar',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(filme),
            success: function (data) {
                console.log("Ola")
                alert('Filme adicionado na API com sucesso!');
                $('#titulo, #sinopse, #genero, #ano').val('');
                
            },
            error: function () {
                alert('Não foi possível criar o filme na API.');
            }
        });

    }


    // Adiciona um filme ao enviar o formulário
    $('#formFilme').submit(function (event) {
        event.preventDefault();
        let titulo = $('#titulo').val();
        let sinopse = $('#sinopse').val();
        let genero = $('#genero').val();
        let ano = $('#ano').val();

        if (!titulo || !sinopse || !genero || !ano) {
            alert('Por favor, preencha todos os campos do filme.');
            return;
        }

        let filme = {
            titulo: titulo,
            sinopse: sinopse,
            genero: genero,
            ano: ano
        };

        criarFilme(filme);
    });
});