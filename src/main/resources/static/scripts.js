// Pobierz listę przepisów z API i wyświetl na stronie
fetch('/api/recipes')
    .then(response => response.json())
    .then(data => {
        const recipeList = document.getElementById('recipe-list');
        recipeList.innerHTML = '';
        data.forEach(recipe => {
            const listItem = document.createElement('li');
            listItem.textContent = `${recipe.name} - ${recipe.category}`;
            recipeList.appendChild(listItem);
        });
    })
    .catch(error => console.error('Error fetching recipes:', error));

// Obsługa formularza dodawania przepisu
document.getElementById('add-recipe-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const recipe = {
        name: document.getElementById('name').value,
        ingredients: document.getElementById('ingredients').value.split(',').map(s => s.trim()),
        instructions: document.getElementById('instructions').value,
        category: document.getElementById('category').value
    };

    fetch('/api/recipes', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(recipe)
    })
    .then(response => {
        if (response.ok) {
            alert('Przepis dodany pomyślnie!');
            location.reload();
        } else {
            alert('Wystąpił błąd przy dodawaniu przepisu.');
        }
    })
    .catch(error => console.error('Error adding recipe:', error));
});

