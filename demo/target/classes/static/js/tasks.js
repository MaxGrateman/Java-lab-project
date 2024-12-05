const taskList = document.getElementById("task-list");
const searchInput = document.getElementById("searchInput");
const filterDropdown = document.getElementById("filterDropdown");
const searchButton = document.getElementById("searchButton");



function displayTasks(tasks) {
    taskList.innerHTML = "";
    if (tasks.length === 0) {
        taskList.innerHTML = "<li>No tasks found.</li>";
        return;
    }

    tasks.forEach((task) => {
        const listItem = document.createElement("li");
        listItem.innerHTML = `
            <div class="task">
                <h3>${task.title}</h3>
                <p>${task.description || "No description"}</p>
                <p><strong>Status:</strong> ${task.status}</p>
                <p><strong>Priority:</strong> ${task.priority}</p>
                <p><strong>Due Date:</strong> ${task.due_date || "N/A"}</p>
            </div>
        `;
        taskList.appendChild(listItem);
    });
}

function updatePagination(data) {
    const prevPageButton = document.getElementById("prevPage");
    const nextPageButton = document.getElementById("nextPage");
    const currentPageSpan = document.getElementById("currentPage");

    currentPage = data.number;

    currentPageSpan.textContent = `Page ${currentPage + 1} of ${data.totalPages}`;
    prevPageButton.disabled = data.first;
    nextPageButton.disabled = data.last;
}

searchButton.addEventListener("click", () => {
    const search = searchInput.value;
    const filter = filterDropdown.value;
    loadTasks(0, search, filter);
});

document.getElementById("prevPage").addEventListener("click", () => {
    if (currentPage > 0) loadTasks(currentPage - 1, searchInput.value, filterDropdown.value);
});

document.getElementById("nextPage").addEventListener("click", () => {
    loadTasks(currentPage + 1, searchInput.value, filterDropdown.value);
});


loadTasks();
