const taskList = document.getElementById("task-list");
const addTaskForm = document.getElementById("add-task-form");

const fetchTasks = async () => {
    const response = await fetch("/api/tasks");
    const tasks = await response.json();
    taskList.innerHTML = "";
    tasks.forEach(task => {
        const li = document.createElement("li");
        li.innerHTML = `
            <strong>${task.title}</strong> - ${task.description}
            <br>Due: ${task.dueDate} | Priority: ${task.priority} | Status: ${task.status}
            <button class="task_button" onclick="deleteTask(${task.id})">Delete</button>
            <button class="task_button" onclick="editTask(${task.id})">Edit</button>
        `;
        taskList.appendChild(li);
    });
};

const addTask = async (event) => {
    event.preventDefault();
    const title = document.getElementById("task-title").value;
    const description = document.getElementById("task-desc").value;
    const dueDate = document.getElementById("task-due-date").value;
    const priority = document.getElementById("task-priority").value;

    const response = await fetch("/api/tasks", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ title, description, dueDate, priority, status: "Pending" }),
    });

    if (response.ok) {
        fetchTasks();
        addTaskForm.reset();
    }
};

const deleteTask = async (taskId) => {
    await fetch(`/api/tasks/${taskId}`, { method: "DELETE" });
    fetchTasks();
};

const editTask = (taskId) => {
    // Implement edit logic with a modal or inline form
};

addTaskForm.addEventListener("submit", addTask);

fetchTasks();