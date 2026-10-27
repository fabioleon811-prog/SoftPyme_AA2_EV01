import { useState } from 'react';
import './App.css';

function App() {
  const [usuario, setUsuario] = useState('');
  const [peso, setPeso] = useState('');
  const [fecha, setFecha] = useState(
    new Date().toISOString().slice(0, 10)
  );
  const [registros, setRegistros] = useState([]);

  // Registra el peso ingresado por el usuario.
  function registrarPeso(e) {
    e.preventDefault();

    if (!usuario.trim() || !peso || Number(peso) <= 0 || !fecha) {
      alert('Completa todos los campos correctamente.');
      return;
    }

    setRegistros([
      ...registros,
      {
        id: Date.now(),
        usuario,
        peso: Number(peso),
        fecha
      }
    ]);

    setPeso('');
  }

  // Elimina un registro seleccionado.
  function eliminarRegistro(id) {
    setRegistros(registros.filter((registro) => registro.id !== id));
  }

  return (
    <main className="contenedor">
      <header className="encabezado">
        <div>
          <p className="etiqueta">BIENESTAR Y SALUD</p>
          <h1>SoftPyme</h1>
          <p>Tu progreso, un día a la vez.</p>
        </div>
        <span className="icono">⚖️</span>
      </header>

      <section className="resumen">
        <article className="tarjeta">
          <span>Registros guardados</span>
          <strong>{registros.length}</strong>
        </article>
        <article className="tarjeta">
          <span>Último peso registrado</span>
          <strong>
            {registros.length
              ? `${registros[registros.length - 1].peso} kg`
              : '-- kg'}
          </strong>
        </article>
      </section>

      <section className="panel">
        <h2>Registrar peso</h2>
        <p className="descripcion">
          Lleva un seguimiento de tu progreso físico.
        </p>

        <form onSubmit={registrarPeso}>
          <label htmlFor="usuario">Nombre del usuario</label>
          <input
            id="usuario"
            value={usuario}
            onChange={(e) => setUsuario(e.target.value)}
            placeholder="Escribe tu nombre"
            required
          />

          <div className="campos">
            <div>
              <label htmlFor="peso">Peso (kg)</label>
              <input
                id="peso"
                type="number"
                min="1"
                max="500"
                step="0.1"
                value={peso}
                onChange={(e) => setPeso(e.target.value)}
                placeholder="Ej. 70.5"
                required
              />
            </div>

            <div>
              <label htmlFor="fecha">Fecha</label>
              <input
                id="fecha"
                type="date"
                value={fecha}
                onChange={(e) => setFecha(e.target.value)}
                required
              />
            </div>
          </div>

          <button type="submit">+ Guardar registro</button>
        </form>
      </section>

      <section className="panel">
        <h2>Historial de peso</h2>

        {registros.length === 0 ? (
          <p className="vacio">
            Todavía no hay registros. ¡Agrega el primero!
          </p>
        ) : (
          <div className="tabla-contenedor">
            <table>
              <thead>
                <tr>
                  <th>Usuario</th>
                  <th>Peso</th>
                  <th>Fecha</th>
                  <th>Acción</th>
                </tr>
              </thead>
              <tbody>
                {registros.map((registro) => (
                  <tr key={registro.id}>
                    <td>{registro.usuario}</td>
                    <td>{registro.peso} kg</td>
                    <td>{registro.fecha}</td>
                    <td>
                      <button
                        type="button"
                        className="boton-eliminar"
                        onClick={() => eliminarRegistro(registro.id)}
                      >
                        Eliminar
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </section>

      <footer>SoftPyme © 2026 · Seguimiento de peso</footer>
    </main>
  );
}

export default App;