const API_BASE = '/api';

// --- Books Page ---
if (document.querySelector('#booksTableBody')) {
    loadBooks();

    async function loadBooks() {
        try {
            const response = await fetch(`${API_BASE}/books`);
            const books = await response.json();
            renderBooks(books);
        } catch (error) {
            console.error('Ошибка загрузки книг:', error);
            document.querySelector('#booksTableBody').innerHTML =
                '<tr><td colspan="5" style="text-align:center;padding:40px;color:#dc3545;">Ошибка загрузки данных</td></tr>';
        }
    }

    function renderBooks(books) {
        const tbody = document.querySelector('#booksTableBody');
        if (!books || books.length === 0) {
            tbody.innerHTML = `<tr><td colspan="5" style="text-align:center;padding:40px;color:#a0aec0;">Книг не найдено</td></tr>`;
            return;
        }

        tbody.innerHTML = books.map(book => `
            <tr>
                <td>${book.bookId || '-'}</td>
                <td><strong>${book.title || ''}</strong></td>
                <td>${book.author || ''}</td>
                <td>${book.isbn || ''}</td>
                <td>${book.year || '-'}</td>
            </tr>
        `).join('');
    }

    document.querySelector('#searchInput')?.addEventListener('input', async function(e) {
        const query = e.target.value.toLowerCase();
        try {
            const response = await fetch(`${API_BASE}/books`);
            const allBooks = await response.json();
            const filtered = allBooks.filter(b =>
                (b.title || '').toLowerCase().includes(query) ||
                (b.author || '').toLowerCase().includes(query) ||
                (b.isbn || '').includes(query)
            );
            renderBooks(filtered);
        } catch (error) {
            console.error('Ошибка поиска:', error);
        }
    });
}

// --- Patrons Page ---
if (document.querySelector('#patronsTableBody')) {
    loadPatrons();

    async function loadPatrons() {
        try {
            const response = await fetch(`${API_BASE}/patrons`);
            const patrons = await response.json();
            renderPatrons(patrons);
        } catch (error) {
            console.error('Ошибка загрузки читателей:', error);
            document.querySelector('#patronsTableBody').innerHTML =
                '<tr><td colspan="5" style="text-align:center;padding:40px;color:#dc3545;">Ошибка загрузки данных</td></tr>';
        }
    }

    function renderPatrons(patrons) {
        const tbody = document.querySelector('#patronsTableBody');
        if (!patrons || patrons.length === 0) {
            tbody.innerHTML = `<tr><td colspan="5" style="text-align:center;padding:40px;color:#a0aec0;">Читателей не найдено</td></tr>`;
            return;
        }

        tbody.innerHTML = patrons.map(patron => `
            <tr>
                <td>${patron.patronId || '-'}</td>
                <td><strong>${patron.patronName || ''}</strong></td>
                <td>${patron.email || ''}</td>
                <td>${patron.phoneNumber || '-'}</td>
                <td>${patron.registrationDate || '-'}</td>
            </tr>
        `).join('');
    }

    document.querySelector('#searchInput')?.addEventListener('input', async function(e) {
        const query = e.target.value.toLowerCase();
        try {
            const response = await fetch(`${API_BASE}/patrons`);
            const allPatrons = await response.json();
            const filtered = allPatrons.filter(p =>
                (p.patronName || '').toLowerCase().includes(query) ||
                (p.email || '').toLowerCase().includes(query)
            );
            renderPatrons(filtered);
        } catch (error) {
            console.error('Ошибка поиска:', error);
        }
    });
}

// --- Authors Page ---
if (document.querySelector('#authorsGrid')) {
    loadAuthors();

    async function loadAuthors() {
        try {
            const response = await fetch(`${API_BASE}/authors`);
            const authors = await response.json();
            renderAuthors(authors);
        } catch (error) {
            console.error('Ошибка загрузки авторов:', error);
            document.querySelector('#authorsGrid').innerHTML =
                '<div style="grid-column:1/-1;text-align:center;padding:60px;color:#dc3545;">Ошибка загрузки данных</div>';
        }
    }

    function renderAuthors(authors) {
        const grid = document.querySelector('#authorsGrid');
        if (!authors || authors.length === 0) {
            grid.innerHTML = `<div style="grid-column:1/-1;text-align:center;padding:60px;color:#a0aec0;">Авторов не найдено</div>`;
            return;
        }

        grid.innerHTML = authors.map(author => `
            <div class="author-card">
                <h3 class="author-name">${author.name || ''}</h3>
                <p class="author-bio">${author.bio || 'Нет биографии'}</p>
            </div>
        `).join('');
    }

    document.querySelector('#searchInput')?.addEventListener('input', async function(e) {
        const query = e.target.value.toLowerCase();
        try {
            const response = await fetch(`${API_BASE}/authors`);
            const allAuthors = await response.json();
            const filtered = allAuthors.filter(a =>
                (a.name || '').toLowerCase().includes(query)
            );
            renderAuthors(filtered);
        } catch (error) {
            console.error('Ошибка поиска:', error);
        }
    });
}

// Обновление статистики на главной
if (document.querySelector('#booksCount')) {
    updateStats();

    async function updateStats() {
        try {
            const [booksRes, patronsRes, authorsRes] = await Promise.all([
                fetch(`${API_BASE}/books`),
                fetch(`${API_BASE}/patrons`),
                fetch(`${API_BASE}/authors`)
            ]);

            const books = await booksRes.json();
            const patrons = await patronsRes.json();
            const authors = await authorsRes.json();

            document.querySelector('#booksCount').textContent = books.length || 0;
            document.querySelector('#patronsCount').textContent = patrons.length || 0;
            document.querySelector('#authorsCount').textContent = authors.length || 0;
        } catch (error) {
            console.error('Ошибка загрузки статистики:', error);
        }
    }
}