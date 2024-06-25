import {axiosInstance} from '../common/axios/axios-instance';
import {AxiosResponse} from 'axios';
import {TaskProps} from '../components/Task';

export interface GetTasksResponseDTO {
  tasks: TaskProps[];
}

export interface CreateTasksRequestDTO {
  titulo: string,
  descricao: string,
  status: string,
  prioridade: string,
}

export interface UpdateTasksRequestDTO {
  titulo: string,
  descricao: string,
  status: string,
  prioridade: string,
}

export const toDoListService = () => {

    const getTasks = async (): Promise<TaskProps[] | undefined> => {
        try {
          const response: AxiosResponse<GetTasksResponseDTO> = await axiosInstance.get<GetTasksResponseDTO>('tarefas');
          return response.data.tasks;
        } catch (error) {
          console.error(error);
        }
    }

    const createTask = async (newTask: CreateTasksRequestDTO): Promise<CreateTasksRequestDTO | undefined> => {
      try {
        const response: AxiosResponse<CreateTasksRequestDTO> = await axiosInstance.post<CreateTasksRequestDTO>('tarefas', newTask);
        return response.data;
      } catch (error) {
        console.error(error);
      }
    };

    const updateTask = async (updatedTask: UpdateTasksRequestDTO, id: number): Promise<TaskProps | undefined> => {
      try {
          const response: AxiosResponse<TaskProps> = await axiosInstance.put<TaskProps>(`tarefas/${id}`, updatedTask);
          return response.data;
      } catch (error) {
          console.error(error);
      }
    };

    const deleteTask = async (id: number): Promise<TaskProps | undefined> => {
      try {
        const response: AxiosResponse<TaskProps> = await axiosInstance.delete<TaskProps>(`tarefas/${id}`);
        return response.data;
      } catch (error) {
        console.error(error);
      }
    };
  
    return {
        getTasks,
        createTask,
        updateTask,
        deleteTask
    };
};

