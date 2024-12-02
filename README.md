# quarkus-ai-llm-gmail-summarizer

This application uses the Gmail API to retrieve email threads and generates concise summaries using Llama 3.2 AI model. Built with Quarkus, it leverages advanced AI capabilities to process email threads and provide meaningful insights.

---

## Features

- **Gmail Integration**: Authenticate via OAuth 2.0 and access email threads.
- **AI Summarization**: Utilize Llama 3.2 to generate concise summaries of email conversations.
- **Simple Setup**: Built with Quarkus for lightweight and efficient execution.

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
  - Add `http://localhost:8080` under "Authorized redirect URIs."
4. Download the `credentials.json` file and place it in your project directory. Place it in the 'src/main/resources' folder.

### Step 3: Configure AI model server

```
# LangChain4j config
quarkus.langchain4j.ollama.chat-model.model-id=llama3.2
quarkus.langchain4j.ollama.timeout=60s
quarkus.langchain4j.ollama.log-responses=true
```

## Step 4: Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

Now we can try it just sending a request for summarizing a thread of emails by subject like this:

```shell script
http GET http://localhost:8080/gmail/summarize\?subject="subject:(your-subject)" | jq
```