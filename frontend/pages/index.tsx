import type { NextPage } from "next";
import Head from "next/head";
import styles from "../styles/Home.module.css";

const Home: NextPage = () => {
  return (
    <div className={styles.container}>
      <Head>
        <title>Demo web app ~ openapi generator</title>
        <meta name="description" content="Demo web app ~ openapi generator" />
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <main className={styles.main}>
        <h1 className={styles.title}>
          Welcome to{" "}
          <a href="https://openapi-generator.tech/docs/generators/typescript-fetch">
            openapi-generator
          </a>
        </h1>
      </main>
    </div>
  );
};

export default Home;
