package com.example.authmetest

object MockResponse {
    val userDetailJson = "{\n" +
            "  \"login\": \"mojombo\",\n" +
            "  \"id\": 1,\n" +
            "  \"node_id\": \"MDQ6VXNlcjE=\",\n" +
            "  \"avatar_url\": \"https://avatars.githubusercontent.com/u/1?v=4\",\n" +
            "  \"gravatar_id\": \"\",\n" +
            "  \"url\": \"https://api.github.com/users/mojombo\",\n" +
            "  \"html_url\": \"https://github.com/mojombo\",\n" +
            "  \"followers_url\": \"https://api.github.com/users/mojombo/followers\",\n" +
            "  \"following_url\": \"https://api.github.com/users/mojombo/following{/other_user}\",\n" +
            "  \"gists_url\": \"https://api.github.com/users/mojombo/gists{/gist_id}\",\n" +
            "  \"starred_url\": \"https://api.github.com/users/mojombo/starred{/owner}{/repo}\",\n" +
            "  \"subscriptions_url\": \"https://api.github.com/users/mojombo/subscriptions\",\n" +
            "  \"organizations_url\": \"https://api.github.com/users/mojombo/orgs\",\n" +
            "  \"repos_url\": \"https://api.github.com/users/mojombo/repos\",\n" +
            "  \"events_url\": \"https://api.github.com/users/mojombo/events{/privacy}\",\n" +
            "  \"received_events_url\": \"https://api.github.com/users/mojombo/received_events\",\n" +
            "  \"type\": \"User\",\n" +
            "  \"site_admin\": false,\n" +
            "  \"name\": \"Tom Preston-Werner\",\n" +
            "  \"company\": \"@chatterbugapp, @redwoodjs, @preston-werner-ventures \",\n" +
            "  \"blog\": \"http://tom.preston-werner.com\",\n" +
            "  \"location\": \"San Francisco\",\n" +
            "  \"email\": null,\n" +
            "  \"hireable\": null,\n" +
            "  \"bio\": null,\n" +
            "  \"twitter_username\": \"mojombo\",\n" +
            "  \"public_repos\": 66,\n" +
            "  \"public_gists\": 62,\n" +
            "  \"followers\": 23812,\n" +
            "  \"following\": 11,\n" +
            "  \"created_at\": \"2007-10-20T05:24:19Z\",\n" +
            "  \"updated_at\": \"2024-02-08T04:58:15Z\"\n" +
            "}"


    val userListJson = "[\n" +
            "  {\n" +
            "    \"login\": \"mojombo\",\n" +
            "    \"id\": 1,\n" +
            "    \"node_id\": \"MDQ6VXNlcjE=\",\n" +
            "    \"avatar_url\": \"https://avatars.githubusercontent.com/u/1?v=4\",\n" +
            "    \"gravatar_id\": \"\",\n" +
            "    \"url\": \"https://api.github.com/users/mojombo\",\n" +
            "    \"html_url\": \"https://github.com/mojombo\",\n" +
            "    \"followers_url\": \"https://api.github.com/users/mojombo/followers\",\n" +
            "    \"following_url\": \"https://api.github.com/users/mojombo/following{/other_user}\",\n" +
            "    \"gists_url\": \"https://api.github.com/users/mojombo/gists{/gist_id}\",\n" +
            "    \"starred_url\": \"https://api.github.com/users/mojombo/starred{/owner}{/repo}\",\n" +
            "    \"subscriptions_url\": \"https://api.github.com/users/mojombo/subscriptions\",\n" +
            "    \"organizations_url\": \"https://api.github.com/users/mojombo/orgs\",\n" +
            "    \"repos_url\": \"https://api.github.com/users/mojombo/repos\",\n" +
            "    \"events_url\": \"https://api.github.com/users/mojombo/events{/privacy}\",\n" +
            "    \"received_events_url\": \"https://api.github.com/users/mojombo/received_events\",\n" +
            "    \"type\": \"User\",\n" +
            "    \"site_admin\": false\n" +
            "  },\n" +
            "  {\n" +
            "    \"login\": \"defunkt\",\n" +
            "    \"id\": 2,\n" +
            "    \"node_id\": \"MDQ6VXNlcjI=\",\n" +
            "    \"avatar_url\": \"https://avatars.githubusercontent.com/u/2?v=4\",\n" +
            "    \"gravatar_id\": \"\",\n" +
            "    \"url\": \"https://api.github.com/users/defunkt\",\n" +
            "    \"html_url\": \"https://github.com/defunkt\",\n" +
            "    \"followers_url\": \"https://api.github.com/users/defunkt/followers\",\n" +
            "    \"following_url\": \"https://api.github.com/users/defunkt/following{/other_user}\",\n" +
            "    \"gists_url\": \"https://api.github.com/users/defunkt/gists{/gist_id}\",\n" +
            "    \"starred_url\": \"https://api.github.com/users/defunkt/starred{/owner}{/repo}\",\n" +
            "    \"subscriptions_url\": \"https://api.github.com/users/defunkt/subscriptions\",\n" +
            "    \"organizations_url\": \"https://api.github.com/users/defunkt/orgs\",\n" +
            "    \"repos_url\": \"https://api.github.com/users/defunkt/repos\",\n" +
            "    \"events_url\": \"https://api.github.com/users/defunkt/events{/privacy}\",\n" +
            "    \"received_events_url\": \"https://api.github.com/users/defunkt/received_events\",\n" +
            "    \"type\": \"User\",\n" +
            "    \"site_admin\": false\n" +
            "  },\n" +
            "  {\n" +
            "    \"login\": \"pjhyett\",\n" +
            "    \"id\": 3,\n" +
            "    \"node_id\": \"MDQ6VXNlcjM=\",\n" +
            "    \"avatar_url\": \"https://avatars.githubusercontent.com/u/3?v=4\",\n" +
            "    \"gravatar_id\": \"\",\n" +
            "    \"url\": \"https://api.github.com/users/pjhyett\",\n" +
            "    \"html_url\": \"https://github.com/pjhyett\",\n" +
            "    \"followers_url\": \"https://api.github.com/users/pjhyett/followers\",\n" +
            "    \"following_url\": \"https://api.github.com/users/pjhyett/following{/other_user}\",\n" +
            "    \"gists_url\": \"https://api.github.com/users/pjhyett/gists{/gist_id}\",\n" +
            "    \"starred_url\": \"https://api.github.com/users/pjhyett/starred{/owner}{/repo}\",\n" +
            "    \"subscriptions_url\": \"https://api.github.com/users/pjhyett/subscriptions\",\n" +
            "    \"organizations_url\": \"https://api.github.com/users/pjhyett/orgs\",\n" +
            "    \"repos_url\": \"https://api.github.com/users/pjhyett/repos\",\n" +
            "    \"events_url\": \"https://api.github.com/users/pjhyett/events{/privacy}\",\n" +
            "    \"received_events_url\": \"https://api.github.com/users/pjhyett/received_events\",\n" +
            "    \"type\": \"User\",\n" +
            "    \"site_admin\": false\n" +
            "  }\n" +
            "]"
}
