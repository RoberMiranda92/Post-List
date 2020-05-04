# Android Coding Demo

<a href="https://codecov.io/gh/RoberMiranda92/Post-List">
  <img src="https://codecov.io/gh/RoberMiranda92/Post-List/branch/develop/graph/badge.svg?token=ZKK03HD0MI" />
</a>

From a high level point of view the demo consists of a list of post, where each post has its own detail.

Inspired in https://materialdesignkit.com/templates/

## Post Screen
A post has a little title and it's up to you how to display it.
To retrieve the post you can use the following API:

 * http://jsonplaceholder.typicode.com/posts

When a post is tapped, you should go to the detail screen.

## Detail Screen
A post detail will have

* Its author
* its description (via de the body)
* The number of comment it has
* The list of comments of the post, where each item of the list will display:
    * Title (via the name)
    * Comment (Via the body)
    * The comment author email, and only in case the emoji mail ends in one of the following formats, we need to display at least one Emoji.
        * ".info" https://emojipedia.org/information/
        * ".co.uk" https://emojipedia.org/flag-united-kingdom/
        * We should be flexible, in the future thoe emoji could be retrieved from our own backend API,
        or we may decide that we don't want to display emojies anymore but some other information, image, custom emojies or something else

To retrieve the rest information
* http://jsonplaceholder.typicode.com/users
* http://jsonplaceholder.typicode.com/users
