# quarkus-ai-llm-gmail-summarizer

This application uses the Gmail API to retrieve email threads and generates concise summaries using the Llama 3.2 AI model. Built with Quarkus, it leverages advanced AI capabilities to process email threads and provide meaningful insights.

---

## Features

- **Gmail Integration**: Authenticate via OAuth 2.0 and access email threads.
- **AI Summarization**: Utilize Llama 3.2 to generate concise summaries of email conversations.
- **Simple Setup**: Built with Quarkus for lightweight and efficient execution.

---

## Prerequisites

1. **Java Development Kit (JDK):**
  - Install JDK 21.
  - Ensure the `JAVA_HOME` environment variable is set.

2. **Maven:**
  - Install Apache Maven.
  - Verify the installation with `mvn -v`.

3. **Google Cloud Project:**
  - A Google Cloud project with the Gmail API enabled.

4. **OAuth 2.0 Credentials:**
  - Download the `credentials.json` file for your OAuth client.

5. **AI Configuration:**
  - Access to the Llama 3.2 API or model endpoint.
  - API key for the Llama AI service.

---

## Setup Instructions

### Step 1: Enable Gmail API

1. Go to the [Google Cloud Console](https://console.cloud.google.com/).
2. Create or select a project.
3. Enable the Gmail API:
  - Navigate to **APIs & Services > Library**.
  - Search for "Gmail API" and enable it.

### Step 2: Create OAuth 2.0 Credentials

1. Navigate to **APIs & Services > Credentials**.
2. Click **Create Credentials > OAuth 2.0 Client IDs**.
3. Configure the OAuth consent screen:
  - Enter a name for the app.
  - Add `http://localhost:8080/callback` under "Authorized redirect URIs."
4. Download the `credentials.json` file and place it in your project directory.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.
