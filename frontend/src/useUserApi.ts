import { Configuration, UserApi, UserDto } from "api-client";
import { useEffect, useState } from "react";

const userApi = new UserApi(
  new Configuration({
    basePath: "http://localhost:8080/api",
  })
);

export const useUserApi = (): UserDto[] => {
  const [users, setUsers] = useState<UserDto[]>([]);

  useEffect(() => {
    userApi.getUsers().then((users) => setUsers(users));
  }, []);

  return users;
};
