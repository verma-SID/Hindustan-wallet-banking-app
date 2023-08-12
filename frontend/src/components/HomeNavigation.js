import { Link, NavLink, useNavigate } from "react-router-dom";
import classes from "./HomeNavigation.module.css";
export default function HomeNavigation() {
  const navigate = useNavigate();
  function LogoutHandler() {
    let value = window.confirm("Are You Sure to Logout");
    if (value) {
      localStorage.removeItem("userMail");
      navigate("/");
      //After Logout navigate to the Welcome Page
    }
  }
  return (
    <nav
      className="navbar navbar-expand-lg bg-body-tertiary"
      style={{ boxShadow: "5px 2px 8px rgba(15, 14, 14, 0.15)" }}
    >
      <div className="container-fluid">
        <Link style={{ textDecoration: "none" }} to="">
          <a className="navbar-brand">HindustanPe</a>
        </Link>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <NavLink
                to="balance"
                className={({ isActive }) =>
                  isActive ? undefined : classes.inactive
                }
                end
              >
                <a className="nav-link active" aria-current="page">
                  Current Balance
                </a>
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink
                to="transactions"
                className={({ isActive }) =>
                  isActive ? undefined : classes.inactive
                }
                end
              >
                <a className="nav-link active" aria-current="page">
                  My Transactions
                </a>
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink
                to="rewards"
                className={({ isActive }) =>
                  isActive ? undefined : classes.inactive
                }
                end
              >
                <a className="nav-link active" aria-current="page">
                  Rewards
                </a>
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink
                to="linked-cards"
                className={({ isActive }) =>
                  isActive ? undefined : classes.inactive
                }
                end
              >
                <a className="nav-link active" aria-current="page">
                  Linked Cards
                </a>
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink
                to="help"
                className={({ isActive }) =>
                  isActive ? undefined : classes.inactive
                }
                end
              >
                <a className="nav-link active" aria-current="page">
                  Help
                </a>
              </NavLink>
            </li>
          </ul>
          <div className="d-flex">
            <button className="btn btn-outline-success" onClick={LogoutHandler}>
              Logout
            </button>
          </div>
        </div>
      </div>
    </nav>
  );
}

//Navigation Bar for the Authenticated Pages
