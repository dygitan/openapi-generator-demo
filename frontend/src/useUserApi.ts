import { useEffect, useState } from "react";

interface UserDto {
  firstName: string;
  lastName: string;
  id: number;
}

export const useUserApi = (): UserDto[] => {
  const [users, setUsers] = useState<UserDto[]>([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/user")
      .then((response) => response.json())
      .then((users) => setUsers(users));
  }, []);

  return users;
};
