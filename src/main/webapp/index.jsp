<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello World</title>
</head>
<body>
<h2>Hello World!</h2>

<form action="/todo/ajoute" method="post">
    <div>
        <label for="todo-item">Definir une tache (global) :</label>
        <input type="text" id="todo-item" name="afaire" />
    </div>
    <br />
    <div>
        <label for="global">Global ? :</label>
        <input type="checkbox" id="global" name="global" value="1" checked="checked" />
    </div>
    <br />
    <div class="button">
        <button type="submit">Envoyer votre tache</button>
    </div>
</form>
</body>
</html>
