function loadOrders() {
    fetch('/orders/all')
        .then(res => res.json())
        .then(data => {
            const tbody = document.querySelector('#ordersTable tbody');
            tbody.innerHTML = "";
            data.forEach(order => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${order.id}</td>
                    <td>${order.customerName}</td>
                    <td>${order.medicineName}</td>
                    <td>${order.quantity}</td>
                    <td>${order.price}</td>
                    <td>${order.orderDate}</td>
                `;
                tbody.appendChild(row);
            });
        });
}

function getInputData() {
    return {
        customerName: document.getElementById("customerName").value,
        medicineName: document.getElementById("medicineName").value,
        quantity: parseInt(document.getElementById("quantity").value),
        price: parseFloat(document.getElementById("price").value)
    };
}

function addOrder() {
    const data = getInputData();
    fetch('/orders/add', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    }).then(() => loadOrders());
}

function updateOrder() {
    const id = document.getElementById("orderId").value;
    if (!id) return alert("Please enter Order ID to update.");
    const data = getInputData();
    fetch(`/orders/update/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    }).then(() => loadOrders());
}

function deleteOrder() {
    const id = document.getElementById("orderId").value;
    if (!id) return alert("Please enter Order ID to delete.");
    fetch(`/orders/delete/${id}`, {
        method: 'DELETE'
    }).then(() => loadOrders());
}

window.onload = loadOrders;
