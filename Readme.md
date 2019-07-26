## Prerequsities:
1. JDK 11
2. Maven 3.6
3. Default port: 8080

### Running on local environment:
mvn spring-boot:run 

### Running in docker container:
If app jar is already built then image must be created:

docker build -t spring/twitter

### If jar is not built:
docker build -t spring/twitter -f Dockerfile.build .

### Running docker image:
docker run -ti --rm --name spring-twitter -p 8080:8080 spring/twitter


## Assumptions:
1. Post cannot be created with empty message.
2. Author cannot be created with empty name and name must be shorter or equal to 20 characters
3. Posts count is not big enough to create pagination.
4. Posts are short lived and older posts are deleted or archived after some time.
5. User is general name for post author.
6. User cannot create self follow. 
7. User cannot unfollow another user without previosuly following user.

### API description:

#### Creating post:
@POST /author/post

Body:
{
	"message":"",
	"author":""
}

Field description:  
 message: String is post content.  
 author: String is author name.
##### Example:
curl -X POST \
  http://localhost:8080/author/post \
  -H 'Content-Type: application/json' \
  -d '{
	"message":"loremIpsum",
	"author":"John01"
}'
##### Example response:
{
    "postId": "5f8e065d-1a1a-4763-81d9-695e6ed6dd0d",
    "authorId": "4e22f62d-0fc2-4f48-8881-74b598721abf"
}

#### Fetching posts by author on wall view
@GET author/{authorId}/wall

AuthorId: String - author unique identification key 

##### Example request:
curl -X GET http://localhost:8080/author/4e22f62d-0fc2-4f48-8881-74b598721abf/wall 
##### Example response:
{
    "authorPosts": [
        {
            "id": 1,
            "content": "loremIpsum",
            "authorName": "John01",
            "authorId": "4e22f62d-0fc2-4f48-8881-74b598721abf"
        }
    ]
}
#### Following another user:
@POST author/{authorId}/favourites/creation

Body:
{
"followedAuthorId":""
}

##### Example request:
curl -X POST \
  http://localhost:8080/author/c438bad0-5764-4d56-8c36-9290bbc5da9c/favourites/creation \
  -H 'Content-Type: application/json' \
  -d '{
	"followedAuthorId":"4e22f62d-0fc2-4f48-8881-74b598721abf"
}'
##### Example response: 
Status:201, No body

#### Unfollowing another user:
@POST author/{authorId}/favourites/deletion

Body:
{
"followedAuthorId":""
}

##### Example request:
curl -X POST \
  http://localhost:8080/author/c438bad0-5764-4d56-8c36-9290bbc5da9c/favourites/deletion \
  -H 'Content-Type: application/json' \
  -d '{
	"followedAuthorId":"4e22f62d-0fc2-4f48-8881-74b598721abf"
}'
##### Example response: 
Status:201, No body

#### Timeline:
@GET author/{authorId}/timeline 

##### Example request:
curl -X GET http://localhost:8080/author/c438bad0-5764-4d56-8c36-9290bbc5da9c/timeline 
##### Example response:
{
    "followingPosts": [
        {
            "id": 2,
            "content": "loremIpsum",
            "authorName": "John01",
            "authorId": "4e22f62d-0fc2-4f48-8881-74b598721abf"
        },
        {
            "id": 1,
            "content": "loremIpsum",
            "authorName": "John01",
            "authorId": "4e22f62d-0fc2-4f48-8881-74b598721abf"
        }
    ]
}