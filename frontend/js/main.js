document.addEventListener('DOMContentLoaded', () => {

    /* =======================
       Login/Register Toggle
    ======================== */
    const showRegister = document.getElementById('showRegister');
    const showLogin = document.getElementById('showLogin');
    const loginBox = document.querySelector('.login-box');
    const registerBox = document.querySelector('.register-box');

    if (showRegister) {
        showRegister.addEventListener('click', () => {
            loginBox.style.display = 'none';
            registerBox.style.display = 'block';
        });
    }

    if (showLogin) {
        showLogin.addEventListener('click', () => {
            registerBox.style.display = 'none';
            loginBox.style.display = 'block';
        });
    }

    /* =======================
       Placeholder Form Submissions
    ======================== */
    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
        form.addEventListener('submit', (e) => {
            e.preventDefault();
            
            const id = form.id;

            // Login form
            if(id === 'loginForm'){
                const role = document.getElementById('roleSelect').value;
                alert('Login submitted! Role: ' + role);

                // Redirect based on role
                if(role === 'admin'){
                    window.location.href = 'admin.html';
                } else {
                    window.location.href = 'dashboard.html';
                }
            }

            // Register form
            else if(id === 'registerForm'){
                alert('Registration submitted! Connect with backend to save user.');
                // Clear form
                form.reset();
            }

            // Investment form
            else if(id === 'investmentForm'){
                alert('Investment submitted! Integrate with backend.');
                form.reset();
            }

            // Goal form
            else if(id === 'goalForm'){
                alert('Goal added! Integrate with backend.');
                form.reset();
            }

            // Scheme form (Admin)
            else if(id === 'schemeForm'){
                alert('Scheme added! Integrate with backend.');
                form.reset();
            }
        });
    });

    /* =======================
       Progress Bars Animation (Goals Page)
    ======================== */
    const progresses = document.querySelectorAll('.progress');
    progresses.forEach(p => {
        const width = p.style.width;
        p.style.width = '0';
        setTimeout(() => { p.style.width = width; }, 100);
    });

    /* =======================
       Dynamic Table Placeholder
       (Example: populate investments, transactions, users)
    ======================== */
    function populateTable(tableId, data){
        const table = document.getElementById(tableId);
        if(!table) return;
        const tbody = table.querySelector('tbody');
        tbody.innerHTML = '';
        data.forEach(row => {
            const tr = document.createElement('tr');
            Object.values(row).forEach(val => {
                const td = document.createElement('td');
                td.textContent = val;
                tr.appendChild(td);
            });
            tbody.appendChild(tr);
        });
    }

    // Example usage for dashboard/investment tables
    const exampleInvestments = [
        { Scheme: 'Scheme A', Amount: '₹5000', 'Start Date': '2025-10-01', 'End Date': '2026-04-01', Status: 'Active' },
        { Scheme: 'Scheme B', Amount: '₹10000', 'Start Date': '2025-09-01', 'End Date': '2026-03-01', Status: 'Active' }
    ];
    populateTable('investmentTable', exampleInvestments);

    const exampleTransactions = [
        { Date: '2025-10-22', Investment: 'Scheme A', Type: 'Deposit', Amount: '₹5000' },
        { Date: '2025-10-23', Investment: 'Scheme B', Type: 'Deposit', Amount: '₹10000' }
    ];
    populateTable('transactionTable', exampleTransactions);

    const exampleUsers = [
        { Name: 'John Doe', Email: 'john@example.com', Role: 'User' },
        { Name: 'Admin', Email: 'admin@example.com', Role: 'Admin' }
    ];
    populateTable('usersTable', exampleUsers);

    const exampleGoals = [
        { Goal: 'Buy Laptop', Target: '₹50000', Saved: '₹20000', Status: 'Active', Progress: '40%' },
        { Goal: 'Vacation', Target: '₹30000', Saved: '₹10000', Status: 'Active', Progress: '33%' }
    ];
    const goalTableBody = document.querySelector('#goalTable tbody');
    if(goalTableBody){
        goalTableBody.innerHTML = '';
        exampleGoals.forEach(goal => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${goal.Goal}</td>
                <td>${goal.Target}</td>
                <td>${goal.Saved}</td>
                <td>${goal.Status}</td>
                <td>
                    <div class="progress-bar">
                        <div class="progress" style="width:${goal.Progress}"></div>
                    </div>
                </td>
            `;
            goalTableBody.appendChild(tr);
        });
    }
});
