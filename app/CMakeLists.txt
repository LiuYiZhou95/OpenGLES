cmake_minimum_required(VERSION 3.4.1)


#源码路径
file (GLOB sourceCode "${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp/*cpp"
                      "${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp/GLUtil/*cpp"
                      "${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp/Shape/*cpp"
            )

#添加源文件或库
add_library( native-lib SHARED ${sourceCode})

target_include_directories( native-lib PRIVATE ${CMAKE_CURRENT_SOURCE_DIR}
                            native-lib PRIVATE $<BUILD_INTERFACE:${CMAKE_CURRENT_SOURCE_DIR}>
                            native-lib PRIVATE $<BUILD_INTERFACE:${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp/>
                            native-lib PRIVATE $<BUILD_INTERFACE:${CMAKE_CURRENT_SOURCE_DIR}/src/main/GLUtil/cpp/>
                            native-lib PRIVATE $<BUILD_INTERFACE:${CMAKE_CURRENT_SOURCE_DIR}/src/main/Shape/cpp/>
                            native-lib PRIVATE $<BUILD_INTERFACE:${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp/glm/gtx>
                            native-lib PRIVATE $<BUILD_INTERFACE:${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp/glm/gtc>
                            native-lib PRIVATE $<BUILD_INTERFACE:${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp/glm/detail>
)
target_link_libraries( native-lib
                       GLESv2
                       android
                       log
                     )