import { useUserApi } from "./useUserApi";

const Users = () => {
  const users = useUserApi();

  return (
    <div style={{ width: "60%" }}>
      <h1>Users</h1>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>First name</th>
            <th>Last name</th>
          </tr>
        </thead>
        <tbody>
          {users?.map((user) => (
            <tr key={user.id}>
              <td>{user.id}</td>
              <td>{user.firstName}</td>
              <td>{user.lastName}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <style jsx>
        {`
          table {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
          }

          table td,
          table th {
            border: 1px solid #ddd;
            padding: 10px;
          }

          table tr:nth-child(even) {
            background-color: #f2f2f2;
          }

          table tr:hover {
            background-color: #ddd;
          }

          table th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #04aa6d;
            color: white;
          }
        `}
      </style>
    </div>
  );
};

export default Users;
