import { useState, useEffect } from 'react';
import { toDoListService } from '../services/toDoListService';
import { TaskProps } from '../components/Task';
import { CreateTasksRequestDTO, UpdateTasksRequestDTO } from '../services/toDoListService';

export const useToDoList = () => {
  const [tasks, setTasks] = useState<TaskProps[]>([]);
  const [isCreateTaskModalOpen, setIsCreateTaskModalOpen] = useState(false);
  const [createTaskFormData, setCreateTaskFormData] = useState<CreateTasksRequestDTO>({
    titulo: '',
    descricao: '',
    prioridade: '',
    dataConclusao: '',
  });
  const [isEditTaskModalOpen, setIsEditTaskModalOpen] = useState(false);
  const [editTaskFormData, setEditTaskFormData] = useState<UpdateTasksRequestDTO>({
    titulo: '',
    descricao: '',
    prioridade: '',
    dataConclusao: '',
    status: '',
  });

  const { deleteTask, updateTask, getTasks, createTask } = toDoListService();
  
  const openCreateTaskModal = () => setIsCreateTaskModalOpen(true);
  const closeCreateTaskModal = () => setIsCreateTaskModalOpen(false);
  const openEditTaskModal = () => setIsEditTaskModalOpen(true);
  const closeEditTaskModal = () => setIsEditTaskModalOpen(false);

  useEffect(() => {
    const fetchTasks = async () => {
      const fetchedTasks = await getTasks();
      if (Array.isArray(fetchedTasks)) {
        setTasks(fetchedTasks);
      }
    };

    fetchTasks();
  }, []);

  const handleCreateTask = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const response = await createTask(createTaskFormData);
      if(response) {
        alert(`Tarefa criada com sucesso!`);
        setIsCreateTaskModalOpen(false);
        setTimeout(() => {
          window.location.reload();
        }, 300);
      }
    } catch (error) {
      alert(`Erro ao criar tarefa: ${error}`);
    }
  };

  const handleCreateTaskFormChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setCreateTaskFormData({
      ...createTaskFormData,
      [name]: value,
    });
  };

  const handleEditTaskFormChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setEditTaskFormData({
      ...editTaskFormData,
      [name]: value,
    });
  };

  const handleUpdateTask = async (e: React.FormEvent, id: number) => {
    e.preventDefault();
    try {
      const response = await updateTask(editTaskFormData, id);
      if (response) {
        alert(`Tarefa atualizada com sucesso!`);
        closeEditTaskModal();
        setTimeout(() => {
          window.location.reload();
        }, 300);
      }
    } catch (error) {
      alert(`Erro ao atualizar tarefa: ${error}`);
    }
  };

  const loadTaskData = (task: TaskProps) => {
    setEditTaskFormData({
      titulo: task.titulo,
      descricao: task.descricao,
      prioridade: task.prioridade,
      dataConclusao: task.dataConclusao,
      status: task.status,
    });
    openEditTaskModal();
  };

  const handleDeleteTask = async (id: number) => {
    try {
      const response = await deleteTask(id);
      if (response) {
        alert(`Tarefa deletada com sucesso!`);
        setTimeout(() => {
          window.location.reload();
        }, 300);
      }
    } catch (error) {
      alert(`Erro ao deletar tarefa: ${error}`);
    }
  };

  return {
    tasks,
    isCreateTaskModalOpen,
    openCreateTaskModal,
    closeCreateTaskModal,
    handleCreateTask,
    createTaskFormData,
    handleCreateTaskFormChange,
    loadTaskData,
    handleUpdateTask,
    isEditTaskModalOpen,
    closeEditTaskModal,
    handleEditTaskFormChange,
    editTaskFormData,
    handleDeleteTask,
  };
};
