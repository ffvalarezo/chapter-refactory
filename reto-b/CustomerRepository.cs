using System.Collections.Generic;
using System.Data;
using Dapper;

namespace Bank.Refactor.Bad
{
    public class CustomerRepository
    {
        private readonly IDbConnection _conn;

        public CustomerRepository(IDbConnection conn) { _conn = conn; }

        public IEnumerable<dynamic> Search(string name, string status)
        {
            var sql = "SELECT c.id, c.name, c.status, a.number AS account " +
                      "FROM customers c LEFT JOIN accounts a ON a.customer_id = c.id WHERE 1=1 ";
            if (!string.IsNullOrWhiteSpace(name))
            {
                sql += $" AND c.name LIKE '%{name}%'";
            }
            if (!string.IsNullOrWhiteSpace(status))
            {
                sql += $" AND c.status = '{status}'";
            }
            sql += " ORDER BY c.id DESC";
            return _conn.Query(sql); // devuelve dynamic sin tipado
        }
    }
}
